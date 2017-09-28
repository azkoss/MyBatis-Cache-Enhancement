package com.github.wanggit.mybatis.cache.enhancement.context.redis;

import com.github.wanggit.mybatis.cache.enhancement.context.ICacheSupportHash;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisCache implements ICacheSupportHash {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCache(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean setNx(String key, String hashExistsKey, Object object) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashExistsKey, object);
    }

    @Override
    public Object get(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void set(String key, String hashKey, Object object) {
        redisTemplate.opsForHash().put(key, hashKey, object);
    }

    @Override
    public void del(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public void expired(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }
}
