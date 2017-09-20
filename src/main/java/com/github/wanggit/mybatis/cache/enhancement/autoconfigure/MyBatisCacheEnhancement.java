package com.github.wanggit.mybatis.cache.enhancement.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mybatis.cache.enhancement")
public class MyBatisCacheEnhancement {

    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
