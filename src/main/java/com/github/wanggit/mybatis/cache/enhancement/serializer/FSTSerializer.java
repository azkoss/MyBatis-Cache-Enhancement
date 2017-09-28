package com.github.wanggit.mybatis.cache.enhancement.serializer;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FSTSerializer implements RedisSerializer {

    private static final Logger logger = LoggerFactory.getLogger(FSTSerializer.class);

    private FSTConfiguration conf;

    public FSTSerializer(FSTConfiguration conf) {
        this.conf = conf;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        ByteArrayOutputStream stream = null;
        try {
            stream = new ByteArrayOutputStream();
            FSTObjectOutput out = conf.getObjectOutput(stream);
            out.writeObject(o);
            // DON'T out.close() when using factory method;
            out.flush();
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (null != stream){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        ByteArrayInputStream stream = null;
        try {
            stream = new ByteArrayInputStream(bytes);
            FSTObjectInput in = conf.getObjectInput(stream);
            return in.readObject();
            // DON'T: in.close(); here prevents reuse and will result in an exception
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if (null != stream){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
