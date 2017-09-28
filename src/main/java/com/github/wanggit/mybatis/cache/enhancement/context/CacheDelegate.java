package com.github.wanggit.mybatis.cache.enhancement.context;

import com.github.wanggit.mybatis.cache.enhancement.utils.XSpringUtils;

import java.util.concurrent.TimeUnit;

public class CacheDelegate {

    public static boolean setQueryFlagNotSuccess(String key, String hashExistsKey){
        ICache cache = XSpringUtils.getCacheImplement();
        if (cache instanceof ICacheSupportHash){
            ICacheSupportHash supportHash = (ICacheSupportHash) cache;
            boolean success = supportHash.setNx(key, hashExistsKey, 0);
            return !success;
        }
        return false;
    }

    public static Object get(String key, String hashKey){
        ICache cache = XSpringUtils.getCacheImplement();
        if (cache instanceof ICacheSupportHash){
            ICacheSupportHash supportHash = (ICacheSupportHash) cache;
            return supportHash.get(key, hashKey);
        }
        return null;
    }


    public static void set(String key, String hashKey, Object object) {
        ICache cache = XSpringUtils.getCacheImplement();
        if (cache instanceof ICacheSupportHash){
            ICacheSupportHash supportHash = (ICacheSupportHash) cache;
            supportHash.set(key, hashKey, object);
        }
    }

    public static void del(String key, String hashKey) {
        ICache cache = XSpringUtils.getCacheImplement();
        if (cache instanceof ICacheSupportHash){
            ICacheSupportHash supportHash = (ICacheSupportHash) cache;
            supportHash.del(key, hashKey);
        }
    }

    public static void expired(String key, long timeout, TimeUnit timeUnit){
        ICache cache = XSpringUtils.getCacheImplement();
        cache.expired(key, timeout, timeUnit);
    }
}
