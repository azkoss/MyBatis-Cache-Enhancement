package com.github.wanggit.mybatis.cache.enhancement.xmybatis;

import com.github.wanggit.mybatis.cache.enhancement.context.CacheContext;
import com.github.wanggit.mybatis.cache.enhancement.context.CacheDelegate;
import com.github.wanggit.mybatis.cache.enhancement.context.ICache;
import com.github.wanggit.mybatis.cache.enhancement.utils.Md5Util;
import com.github.wanggit.mybatis.cache.enhancement.utils.ThreadUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.util.StringUtils;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
})
public class StatementHandlerQueryInterceptor extends StatementHandlerBaseInterceptor {

    private static final Log logger = LogFactory.getLog(StatementHandlerQueryInterceptor.class);

    private int timeout = 10;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String key = CacheContext.get();
        // 判断当前key是否已配置缓存
        CacheContext.CacheMeta cacheMeta = CacheContext.getCacheMeta(key);
        if (null == cacheMeta){
            // 如果不需要使用缓存那么直接返回结果
            return invocation.proceed();
        }
        String hashKey = super.calcCacheKey(invocation);
        String hashExistsKey = hashKey + ICache.KEY_EXISTS_POSTFIX;
        // 使用MD5把key缩短
        hashKey = Md5Util.md5(hashKey);
        hashExistsKey = Md5Util.md5(hashExistsKey);
        // 如果缓存中已存在数据，那么直接返回数据
        Object cacheData = CacheDelegate.get(key, hashKey);
        if (null != cacheData){
            if (logger.isDebugEnabled()){
                logger.debug(ThreadUtils.getCurrentThreadInfo()
                        + " [key:" + key + ", hashKey:" + hashKey + "] hit in cache.");
            }
            return cacheData;
        }else {
            // 缓存中没有数据，那么判断是否有线程正在查询数据
            if (CacheDelegate.setQueryFlagNotSuccess(key, hashExistsKey)){
                if (logger.isDebugEnabled()){
                    logger.debug(ThreadUtils.getCurrentThreadInfo()
                            + " [key:" + key + ", hashKey:" + hashKey + "] waiting other thread querying data to cache.");
                }
                // while 循环检测其他线程是否已把数据查询到缓存
                while (CacheDelegate.setQueryFlagNotSuccess(key, hashExistsKey)){
                    // 已有线程正在查询数据，那么等待数据进入缓存
                    ThreadUtils.pause(timeout);
                }
                // 退出while循环，表明缓存中已存在数据
                return CacheDelegate.get(key, hashKey);
            }
            // 没有其他线程在查询数据到缓存，那么当前线程查询数据到缓存
            else {
                if (logger.isDebugEnabled()){
                    logger.debug(ThreadUtils.getCurrentThreadInfo()
                            + " [key:" + key + ", hashKey:" + hashKey + "] current thread querying data from database and save to cache.");
                }
                // 从数据库查询数据
                Object object = invocation.proceed();
                // 保存数据到缓存
                CacheDelegate.set(key, hashKey, object);
                // 判断是否需要设置过期时间
                if (cacheMeta.getTimeout() > 0){
                    CacheDelegate.expired(key, cacheMeta.getTimeout(), cacheMeta.getTimeUnit());
                }
                // 删除当前线程正在查询数据到缓存的标记
                CacheDelegate.del(key, hashExistsKey);
                if (logger.isDebugEnabled()){
                    logger.debug(ThreadUtils.getCurrentThreadInfo()
                            + " [key:" + key + ", hashKey:" + hashKey + "] current thread saved data to cache.");
                }
                return object;
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String timeoutStr = properties.getProperty("timeout");
        if (StringUtils.hasText(timeoutStr)){
            try {
                this.timeout = Integer.parseInt(timeoutStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
