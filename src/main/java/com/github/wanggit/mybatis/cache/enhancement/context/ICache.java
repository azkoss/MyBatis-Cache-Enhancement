package com.github.wanggit.mybatis.cache.enhancement.context;

import java.util.concurrent.TimeUnit;

public interface ICache {

    String KEY_EXISTS_POSTFIX = "$$keyIsExists$$";

    void expired(String key, long timeout, TimeUnit timeUnit);
}
