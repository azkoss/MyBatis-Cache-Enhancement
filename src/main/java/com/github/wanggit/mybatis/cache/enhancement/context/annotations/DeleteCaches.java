package com.github.wanggit.mybatis.cache.enhancement.context.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeleteCaches {

    DeleteCache[] deleteCache();

}
