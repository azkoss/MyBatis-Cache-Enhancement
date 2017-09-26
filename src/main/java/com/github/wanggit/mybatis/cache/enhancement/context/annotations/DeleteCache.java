package com.github.wanggit.mybatis.cache.enhancement.context.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeleteCache {

    /**
     * Delete cache with keys when the annotation marked method invoked.
     * @return will delete cache keys
     */
    String[] delete() default {};

    Class clazz() default Object.class;

}
