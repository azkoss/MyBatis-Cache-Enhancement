package com.github.wanggit.mybatis.cache.enhancement;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.context.WebApplicationContext;

public abstract class AbsRedisConfigTests {

    @Test
    public void testRedisConfig(){
        Environment environment = getContext().getEnvironment();
        Assert.assertTrue("true".equals(environment.getProperty("cache.enhancement.redis.enable")));
        Assert.assertTrue(getClassName().equals(environment.getProperty("cache.enhancement.redis.serializer")));
        Object object = getContext().getBean("cacheEnhancementRedisTemplate", RedisTemplate.class);
        Assert.assertNotNull(object);
        Assert.assertTrue(object instanceof RedisTemplate);
        object = getContext().getBean(getBeanName(), RedisSerializer.class);
        Assert.assertNotNull(object);
        Assert.assertTrue(object instanceof RedisSerializer);
    }

    public abstract String getBeanName();

    public abstract String getClassName();

    public abstract WebApplicationContext getContext();

}
