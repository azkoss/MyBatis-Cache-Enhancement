package com.github.wanggit.mybatis.cache.enhancement.context;

public interface ICacheSupportHash extends ICache {

    boolean setNx(String key, String hashExistsKey, Object object);

    Object get(String key, String hashKey);

    void set(String key, String hashKey, Object object);

    void del(String key, String hashKey);
}
