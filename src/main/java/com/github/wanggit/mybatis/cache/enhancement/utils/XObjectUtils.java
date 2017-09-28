package com.github.wanggit.mybatis.cache.enhancement.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class XObjectUtils {

    public static String safeToString(Object object){
        if (null == object){
            return "";
        }else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(object);
                return json;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
