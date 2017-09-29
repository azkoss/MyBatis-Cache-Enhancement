package com.github.wanggit.mybatis.cache.enhancement.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Md5Util {

    private static final Log logger = LogFactory.getLog(Md5Util.class);

    public static String md5(String content){
        if (null == content){
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            byte[] hash = digest.digest();
            String md5 = Hex.encodeHexString(hash);
            if (logger.isDebugEnabled()){
                logger.debug(md5 + " [" + content + "]" );
            }
            return md5;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
