package com.github.wanggit.mybatis.cache.enhancement.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CacheEnhancementProperties.CACHE_ENHANCEMENT)
public class CacheEnhancementProperties {

    public static final String CACHE_ENHANCEMENT = "cache.enhancement";

    private boolean enable;

    private Redis redis;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Redis getRedis() {
        return redis;
    }

    public void setRedis(Redis redis) {
        this.redis = redis;
    }

    public static class Redis{
        private boolean enable;
        private String serializer;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getSerializer() {
            return serializer;
        }

        public void setSerializer(String serializer) {
            this.serializer = serializer;
        }
    }
}
