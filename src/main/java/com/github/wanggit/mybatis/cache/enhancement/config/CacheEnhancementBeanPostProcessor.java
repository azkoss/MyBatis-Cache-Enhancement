package com.github.wanggit.mybatis.cache.enhancement.config;

import com.github.wanggit.mybatis.cache.enhancement.context.CacheContext;
import com.github.wanggit.mybatis.cache.enhancement.context.annotations.Cache;
import com.github.wanggit.mybatis.cache.enhancement.context.annotations.DeleteCache;
import org.mybatis.spring.mapper.MapperFactoryBean;
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

@Component
public class CacheEnhancementBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

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
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(mapperClass);
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            Cache cache = AnnotationUtils.findAnnotation(method, Cache.class);
            DeleteCache deleteCache = AnnotationUtils.findAnnotation(method, DeleteCache.class);
            if (null != cache){
                CacheContext.CacheMeta cacheMeta = new CacheContext.CacheMeta();
                cacheMeta.setDeleteWith(cache.deleteWith());
                cacheMeta.setTimeout(cache.timeout());
                cacheMeta.setTimeUnit(cache.timeUnit());
                cacheMeta.setMethodName(methodName);
                CacheContext.addCacheMeta(cacheMeta);
            }
            if (null != deleteCache){

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
