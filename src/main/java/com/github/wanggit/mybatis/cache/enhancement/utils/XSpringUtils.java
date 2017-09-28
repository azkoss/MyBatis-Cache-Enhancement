package com.github.wanggit.mybatis.cache.enhancement.utils;

import com.github.wanggit.mybatis.cache.enhancement.context.ICache;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class XSpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        XSpringUtils.context = applicationContext;
    }

    public static ICache getCacheImplement(){
        return context.getBean(ICache.class);
    }

}
