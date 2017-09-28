package com.github.wanggit.mybatis.cache.enhancement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"redisJdkConf", "test"})
public class RedisJdkConfigTests extends AbsRedisConfigTests {

    @Autowired
    private WebApplicationContext context;

    private static final String CLASS_NAME = "org.springframework.data.redis.serializer.JdkSerializationRedisSerializer";

    @Test
    public void testActiveProfiles(){
        String[] profiles = context.getEnvironment().getActiveProfiles();
        Assert.assertArrayEquals(new String[]{"redisJdkConf", "test"}, profiles);
    }

    public String getBeanName() {
         return "cacheEnhancementJdkSerializationRedisSerializer";
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
