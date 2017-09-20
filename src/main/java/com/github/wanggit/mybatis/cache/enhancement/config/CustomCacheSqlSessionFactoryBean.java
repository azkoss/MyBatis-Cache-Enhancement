package com.github.wanggit.mybatis.cache.enhancement.config;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

public class CustomCacheSqlSessionFactoryBean extends SqlSessionFactoryBean {

    @Override
    public SqlSessionFactory getObject() throws Exception {
        SqlSessionFactory sessionFactory = super.getObject();
        Configuration configuration = sessionFactory.getConfiguration();
        configuration.getMappedStatements().forEach(it -> {

        });



        return sessionFactory;
    }
}
