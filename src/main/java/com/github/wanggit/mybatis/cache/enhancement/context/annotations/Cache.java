package com.github.wanggit.mybatis.cache.enhancement.context.annotations;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    /**
     * Cache timeout
     * @return timeout, default -1 never expired
     */
    int timeout() default -1;

    /**
     * Cache timeout timeunit
     * @return timeunit, default TimeUnit.SECONDS
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * Delete the cache with some event occurred.
     * example:
     * <code>
     *     \@Cache(deleteWith={"xxxInsert", "xxxUpdate", "xxxDelete"})
     * </code>
     * The will delete when xxxInsert or xxxUpdate or xxxDelete method invoked.
     * @return event names
     */
    String[] deleteWith() default {};

}
