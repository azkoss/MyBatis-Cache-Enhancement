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
public class RedisJackson2JsonConfigTests extends AbsRedisConfigTests {

    @Autowired
    private WebApplicationContext context;

    private static final String CLASS_NAME = "org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer";

    @Test
    public void testActiveProfiles(){
        String[] profiles = context.getEnvironment().getActiveProfiles();
        Assert.assertArrayEquals(new String[]{"redisJacksonConf", "test"}, profiles);
    }

    @Override
    public String getBeanName() {
        return "cacheEnhancementJackson2JsonRedisSerializer";
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public WebApplicationContext getContext() {
        return context;
    }

}
