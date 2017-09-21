package com.github.wanggit.mybatis.cache.enhancement.autoconfigure;

import com.github.wanggit.mybatis.cache.enhancement.xmybatis.xspring.XSqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyBatisCacheEnhancement.class)
@ConditionalOnProperty(prefix = "mybatis.cache.enhancement", value = "enable", havingValue = "true")
public class MyBatisCacheEnhancementAutoConfiguration {

    @Bean
    public SqlSessionFactoryBean xsqlSessionFactoryBean(){
        return new XSqlSessionFactoryBean();
    }

}
