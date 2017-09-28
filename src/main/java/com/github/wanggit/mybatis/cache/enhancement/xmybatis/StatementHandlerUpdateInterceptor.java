package com.github.wanggit.mybatis.cache.enhancement.xmybatis;

import com.github.wanggit.mybatis.cache.enhancement.context.CacheContext;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
public class StatementHandlerUpdateInterceptor extends StatementHandlerBaseInterceptor {

    private static final Log logger = LogFactory.getLog(StatementHandlerUpdateInterceptor.class);

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
