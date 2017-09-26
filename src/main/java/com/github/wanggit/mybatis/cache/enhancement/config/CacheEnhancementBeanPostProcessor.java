package com.github.wanggit.mybatis.cache.enhancement.config;

import com.github.wanggit.mybatis.cache.enhancement.context.CacheContext;
import com.github.wanggit.mybatis.cache.enhancement.context.annotations.Cache;
import com.github.wanggit.mybatis.cache.enhancement.context.annotations.DeleteCache;
import com.github.wanggit.mybatis.cache.enhancement.context.annotations.DeleteCaches;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

public class CacheEnhancementBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(CacheEnhancementBeanPostProcessor.class);

    private ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(MapperFactoryBean.class)){
            Object object = context.getBean(beanName);
            Class mapperClass = getMapperInterfacesClazz(object.getClass());
            cacheMeta(mapperClass);
        }
        return bean;
    }

    private void cacheMeta(Class mapperClass){
        String clazzFullName = mapperClass.getName();
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(mapperClass);
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            Cache cache = AnnotationUtils.findAnnotation(method, Cache.class);
            DeleteCaches deleteCaches = AnnotationUtils.findAnnotation(method, DeleteCaches.class);
            if (null != cache){
                CacheContext.CacheMeta cacheMeta = new CacheContext.CacheMeta();
                cacheMeta.setTimeout(cache.timeout());
                cacheMeta.setTimeUnit(cache.timeUnit());
                cacheMeta.setMethodName(methodName);
                cacheMeta.setFullName(clazzFullName + "." + methodName);
                CacheContext.addCacheMeta(cacheMeta);
                if (logger.isDebugEnabled()){
                    logger.debug("CacheMeta: [" + cacheMeta.toString() + "]");
                }
            }
            if (null != deleteCaches){
                DeleteCache[] dcs = deleteCaches.deleteCache();
                for (int a = 0; a < dcs.length; a++) {
                    DeleteCache dc = dcs[i];
                    CacheContext.DeleteCacheMeta deleteCacheMeta = new CacheContext.DeleteCacheMeta();
                    deleteCacheMeta.setTargetDeletes(dc.delete());
                    deleteCacheMeta.setMethodName(methodName);
                    deleteCacheMeta.setFullName(clazzFullName + "." + methodName);
                    if (Object.class.equals(dc.clazz())){
                        deleteCacheMeta.setTargetClazz(mapperClass);
                    }else {
                        deleteCacheMeta.setTargetClazz(dc.clazz());
                    }
                    CacheContext.addDeleteCacheMeta(deleteCacheMeta);
                    if (logger.isDebugEnabled()){
                        logger.debug("DeleteCache: [" + deleteCacheMeta + "]");
                    }
                }
            }
        }
    }


    private Class getMapperInterfacesClazz(Class clazz){
        Class[] classes = ClassUtils.getAllInterfacesForClass(clazz);
        for (int i = 0; i < classes.length; i++) {
            if (!Serializable.class.equals(classes[i])){
                return classes[i];
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
