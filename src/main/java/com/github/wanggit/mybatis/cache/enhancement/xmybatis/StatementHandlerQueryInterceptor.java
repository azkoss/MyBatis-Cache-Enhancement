package com.github.wanggit.mybatis.cache.enhancement.xmybatis;

import com.github.wanggit.mybatis.cache.enhancement.context.CacheContext;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
})
public class StatementHandlerQueryInterceptor extends StatementHandlerBaseInterceptor {

    private static final Log logger = LogFactory.getLog(StatementHandlerQueryInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String cacheKey = super.calcCacheKey(invocation);
        logger.debug(CacheContext.get());
        logger.debug(cacheKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
