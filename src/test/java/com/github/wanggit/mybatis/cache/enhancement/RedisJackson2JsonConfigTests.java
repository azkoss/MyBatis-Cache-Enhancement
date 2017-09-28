package com.github.wanggit.mybatis.cache.enhancement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"redisJacksonConf", "test"})
public class RedisJackson2JsonConfigTests {

    @Autowired
    private WebApplicationContext context;

    private static final String CLASS_NAME = "org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer";

    @Test
    public void testActiveProfiles(){
        String[] profiles = context.getEnvironment().getActiveProfiles();
        Assert.assertArrayEquals(new String[]{"redisJacksonConf", "test"}, profiles);
    }

    @Test
    public void testRedisConfig(){
        Environment environment = context.getEnvironment();
        Assert.assertTrue("true".equals(environment.getProperty("cache.enhancement.redis.enable")));
        Assert.assertTrue(CLASS_NAME.equals(environment.getProperty("cache.enhancement.redis.serializer")));
        Object object = context.getBean("cacheEnhancementRedisTemplate", RedisTemplate.class);
        Assert.assertNotNull(object);
        Assert.assertTrue(object instanceof RedisTemplate);
        object = context.getBean("cacheEnhancementJackson2JsonRedisSerializer", RedisSerializer.class);
        Assert.assertNotNull(object);
        Assert.assertTrue(object instanceof RedisSerializer);
    }

}
