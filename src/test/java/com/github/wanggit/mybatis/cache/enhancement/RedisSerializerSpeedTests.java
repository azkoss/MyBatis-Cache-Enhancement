package com.github.wanggit.mybatis.cache.enhancement;

import com.github.wanggit.mybatis.cache.enhancement.autoconfigure.CacheEnhancementAutoConfiguration;
import com.github.wanggit.mybatis.cache.enhancement.dao.entity.Account;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.nustaq.serialization.FSTConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.StopWatch;

import java.util.*;

@RunWith(JUnit4.class)
public class RedisSerializerSpeedTests {

    private static final Logger logger = LoggerFactory.getLogger(RedisSerializerSpeedTests.class);

    @Test
    public void compare(){
        // create objects
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            Map<String, Object> nest1 = new HashMap<>();
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest1.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put("nest1", nest1);
            Map<String, Object> nest2 = new HashMap<>();
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), null);
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            nest2.put(RandomStringUtils.randomAlphanumeric(30), RandomStringUtils.randomAlphanumeric(100));
            data.put("nest2", nest2);
            List<Account> accounts = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                Account account = new Account();
                account.setEmail(RandomStringUtils.randomAlphanumeric(50));
                account.setId(RandomUtils.nextInt());
                account.setLastLoginTime(new Date());
                account.setName(RandomStringUtils.randomAlphanumeric(50));
                account.setPwd(RandomStringUtils.randomAlphanumeric(50));
                accounts.add(account);
            }
            data.put("accounts", accounts);
            datas.add(data);
        }
        StopWatch stopWatch = new StopWatch();
        CacheEnhancementAutoConfiguration.XHashValueSerializer xHashValueSerializer = new CacheEnhancementAutoConfiguration.XHashValueSerializer();
        stopWatch.start("serialize " + CacheEnhancementAutoConfiguration.XHashValueSerializer.class.getName());
        byte[] bytes = xHashValueSerializer.serialize(datas);
        logger.info("XHashValueSerializer: " + bytes.length);
        stopWatch.stop();
        stopWatch.start("deserialize " + CacheEnhancementAutoConfiguration.XHashValueSerializer.class.getName());
        Object object = xHashValueSerializer.deserialize(bytes);
        stopWatch.stop();
        Assert.assertTrue(object instanceof List);

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        stopWatch.start("serialize " + GenericJackson2JsonRedisSerializer.class.getName());
        bytes = genericJackson2JsonRedisSerializer.serialize(datas);
        logger.info("GenericJackson2JsonRedisSerializer: " + bytes.length);
        stopWatch.stop();
        stopWatch.start("deserialize " + GenericJackson2JsonRedisSerializer.class.getName());
        object = genericJackson2JsonRedisSerializer.deserialize(bytes);
        stopWatch.stop();
        Assert.assertTrue(object instanceof List);

        /*GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(List.class);
        stopWatch.start("serialize " + GenericToStringSerializer.class.getName());
        bytes = genericToStringSerializer.serialize(datas);
        stopWatch.stop();
        stopWatch.start("deserialize " + GenericToStringSerializer.class.getName());
        object = genericToStringSerializer.deserialize(bytes);
        stopWatch.stop();
        Assert.assertTrue(object instanceof List);*/

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        stopWatch.start("serialize " + Jackson2JsonRedisSerializer.class.getName());
        bytes = jackson2JsonRedisSerializer.serialize(datas);
        logger.info("Jackson2JsonRedisSerializer: " + bytes.length);
        stopWatch.stop();
        stopWatch.start("deserialize " + Jackson2JsonRedisSerializer.class.getName());
        object = jackson2JsonRedisSerializer.deserialize(bytes);
        stopWatch.stop();
        Assert.assertTrue(object instanceof List);

        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        stopWatch.start("serialize " + JdkSerializationRedisSerializer.class.getName());
        bytes = jdkSerializationRedisSerializer.serialize(datas);
        logger.info("JdkSerializationRedisSerializer: " + bytes.length);
        stopWatch.stop();
        stopWatch.start("deserialize " + JdkSerializationRedisSerializer.class.getName());
        object = jdkSerializationRedisSerializer.deserialize(bytes);
        stopWatch.stop();
        Assert.assertTrue(object instanceof List);

        /*OxmSerializer oxmSerializer = new OxmSerializer();
        stopWatch.start("serialize " + OxmSerializer.class.getName());
        bytes = oxmSerializer.serialize(datas);
        stopWatch.stop();
        stopWatch.start("deserialize " + OxmSerializer.class.getName());
        object = oxmSerializer.deserialize(bytes);
        stopWatch.stop();
        Assert.assertTrue(object instanceof List);*/

        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
        stopWatch.start("serialize " + FSTConfiguration.class.getName());
        bytes = conf.asByteArray(datas);
        logger.info("FSTConfiguration: " + bytes.length);
        stopWatch.stop();
        stopWatch.start("deserialize " + FSTConfiguration.class.getName());
        object = conf.asObject(bytes);
        stopWatch.stop();
        Assert.assertTrue(object instanceof List);


        System.out.println(stopWatch.prettyPrint());

    }

}
