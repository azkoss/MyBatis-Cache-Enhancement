package com.github.wanggit.mybatis.cache.enhancement;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.StopWatch;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RunWith(JUnit4.class)
public class NormalTests {

    @Test
    public void testMd5(){
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("MD5");
            for (int i=0;i<10000;i++){
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(RandomStringUtils.randomAlphanumeric(300).getBytes());
                byte[] hash = digest.digest();
                String md5 = new String(hash);
            }
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
