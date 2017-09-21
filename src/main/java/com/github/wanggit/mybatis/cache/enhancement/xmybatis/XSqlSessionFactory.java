package com.github.wanggit.mybatis.cache.enhancement.xmybatis;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

public class XSqlSessionFactory extends DefaultSqlSessionFactory {
    public XSqlSessionFactory(Configuration configuration) {
        super(configuration);
    }
}
