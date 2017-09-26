package com.github.wanggit.mybatis.cache.enhancement.autoconfigure;

import com.github.wanggit.mybatis.cache.enhancement.config.CacheEnhancementBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CacheEnhancementProperties.class)
@ConditionalOnProperty(prefix = "cache.enhancement", value = "enable", havingValue = "true")
public class CacheEnhancementAutoConfiguration {

    @Bean
    public CacheEnhancementBeanPostProcessor cacheEnhancementBeanPostProcessor(){
        return new CacheEnhancementBeanPostProcessor();
    }

}
