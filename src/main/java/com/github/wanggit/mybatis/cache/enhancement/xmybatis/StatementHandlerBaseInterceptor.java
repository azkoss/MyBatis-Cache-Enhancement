package com.github.wanggit.mybatis.cache.enhancement.xmybatis;

import com.github.wanggit.mybatis.cache.enhancement.utils.XObjectUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.List;

public abstract class StatementHandlerBaseInterceptor implements Interceptor {

    protected String calcCacheKey(Invocation invocation){
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();
        List<ParameterMapping> mappings = statementHandler.getBoundSql().getParameterMappings();
        Object object = statementHandler.getBoundSql().getParameterObject();
        String cacheKey = sql.replaceAll("\\s+", " ");
        for (ParameterMapping mapping : mappings) {
            cacheKey += mapping.toString();
        }
        cacheKey += XObjectUtils.safeToString(object);
        return cacheKey;
    }

}
