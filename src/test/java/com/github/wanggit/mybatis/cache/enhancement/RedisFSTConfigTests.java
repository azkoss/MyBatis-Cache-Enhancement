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
@ActiveProfiles({"redisFSTConf", "test"})
public class RedisFSTConfigTests extends AbsRedisConfigTests {

    @Autowired
    private WebApplicationContext context;

    private static final String CLASS_NAME = "com.github.wanggit.mybatis.cache.enhancement.serializer.FSTSerializer";

    @Test
    public void testActiveProfiles(){
        String[] profiles = context.getEnvironment().getActiveProfiles();
        Assert.assertArrayEquals(new String[]{"redisFSTConf", "test"}, profiles);
    }

    public String getBeanName() {
         return "cacheEnhancementFSTSerializer";
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
