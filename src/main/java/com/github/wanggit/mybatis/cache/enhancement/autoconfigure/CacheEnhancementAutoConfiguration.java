package com.github.wanggit.mybatis.cache.enhancement.autoconfigure;

import com.github.wanggit.mybatis.cache.enhancement.config.CacheEnhancementBeanPostProcessor;
import com.github.wanggit.mybatis.cache.enhancement.serializer.FSTSerializer;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.io.*;

@Configuration
@EnableConfigurationProperties(CacheEnhancementProperties.class)
@ConditionalOnProperty(prefix = "cache.enhancement", value = "enable", havingValue = "true")
public class CacheEnhancementAutoConfiguration {

    @Bean
    public CacheEnhancementBeanPostProcessor cacheEnhancementBeanPostProcessor(){
        return new CacheEnhancementBeanPostProcessor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "cache.enhancement", value = "redis.enable", havingValue = "true")
    public RedisTemplate<String, Object> cacheEnhancementRedisTemplate(RedisConnectionFactory redisConnectionFactory
            , RedisSerializer redisSerializer){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        return redisTemplate;
    }

    @Bean
    @ConditionalOnBean(name = "cacheEnhancementRedisTemplate", value = RedisTemplate.class)
    @ConditionalOnProperty(prefix = "cache.enhancement", value = "redis.enable", havingValue = "true")
    @ConditionalOnExpression("'${cache.enhancement.redis.serializer}'.equals('org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer')")
    public Jackson2JsonRedisSerializer<Object> cacheEnhancementJackson2JsonRedisSerializer(){
        return new Jackson2JsonRedisSerializer(Object.class);
    }

    @Bean
    @ConditionalOnBean(name = "cacheEnhancementRedisTemplate", value = RedisTemplate.class)
    @ConditionalOnProperty(prefix = "cache.enhancement", value = "redis.enable", havingValue = "true")
    @ConditionalOnExpression("'${cache.enhancement.redis.serializer}'.equals('org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer')")
    public GenericJackson2JsonRedisSerializer cacheEnhancementGenericJackson2JsonRedisSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    @ConditionalOnBean(name = "cacheEnhancementRedisTemplate", value = RedisTemplate.class)
    @ConditionalOnProperty(prefix = "cache.enhancement", value = "redis.enable", havingValue = "true")
    @ConditionalOnExpression("'${cache.enhancement.redis.serializer}'.equals('org.springframework.data.redis.serializer.JdkSerializationRedisSerializer')")
    public JdkSerializationRedisSerializer cacheEnhancementJdkSerializationRedisSerializer(){
        return new JdkSerializationRedisSerializer();
    }

    @Bean
    @ConditionalOnBean(name = "cacheEnhancementRedisTemplate", value = RedisTemplate.class)
    @ConditionalOnProperty(prefix = "cache.enhancement", value = "redis.enable", havingValue = "true")
    @ConditionalOnExpression("'${cache.enhancement.redis.serializer}'.equals('com.github.wanggit.mybatis.cache.enhancement.serializer.FSTSerializer')")
    public FSTSerializer cacheEnhancementFSTSerializer(){
        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
        return new FSTSerializer(conf);
    }

    @Deprecated
    public static class XHashValueSerializer implements RedisSerializer<Object>{

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            ByteArrayOutputStream out = null;
            ObjectOutputStream output = null;
            try {
                out = new ByteArrayOutputStream();
                output = new ObjectOutputStream(out);
                output.writeObject(o);
                return out.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (null != out){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != output){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return new byte[0];
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            ByteArrayInputStream in = null;
            ObjectInputStream input = null;
            try {
                in = new ByteArrayInputStream(bytes);
                input = new ObjectInputStream(in);
                return input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                if (null != in){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != input){
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }

}
