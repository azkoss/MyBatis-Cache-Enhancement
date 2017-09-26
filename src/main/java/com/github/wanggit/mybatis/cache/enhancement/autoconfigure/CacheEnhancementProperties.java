package com.github.wanggit.mybatis.cache.enhancement.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CacheEnhancementProperties.MYBATIS_PREFIX)
public class CacheEnhancementProperties {

    public static final String MYBATIS_PREFIX = "cache.enhancement";

    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
