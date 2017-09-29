package com.github.wanggit.mybatis.cache.enhancement.xmybatis;

import com.github.wanggit.mybatis.cache.enhancement.context.CacheContext;
import com.github.wanggit.mybatis.cache.enhancement.context.CacheDelegate;
import com.github.wanggit.mybatis.cache.enhancement.context.annotations.Cache;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
public class StatementHandlerUpdateInterceptor extends StatementHandlerBaseInterceptor {

    private static final Log logger = LogFactory.getLog(StatementHandlerUpdateInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String key = CacheContext.get();
        // 判断当前是否有需要处理的缓存
        List<CacheContext.DeleteCacheMeta> deleteCacheMetas = CacheContext.getDeleteCacheMetas(key);
        Object object = invocation.proceed();
        // 表示需要有缓存处理
        if (null != deleteCacheMetas && deleteCacheMetas.size() > 0){
            for (int i = 0; i < deleteCacheMetas.size(); i++) {
                CacheContext.DeleteCacheMeta meta = deleteCacheMetas.get(i);
                String[] targetDeletes = meta.getFullTargets();
                // 删除指定的缓存
                CacheDelegate.del(targetDeletes);
            }
        }
        return object;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
