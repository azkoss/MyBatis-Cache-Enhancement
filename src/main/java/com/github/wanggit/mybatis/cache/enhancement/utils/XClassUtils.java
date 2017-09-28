package com.github.wanggit.mybatis.cache.enhancement.utils;

import org.springframework.util.ClassUtils;

public abstract class XClassUtils {

    public static Class getOriginalClazz(Object object){
        Class clazz = object.getClass();
        while (ClassUtils.isCglibProxyClass(clazz)){
            clazz = clazz.getSuperclass();
        }
        return clazz;
    }

    public static Class[] getInterfaces(Class clazz){
        return clazz.getInterfaces();
    }


}
