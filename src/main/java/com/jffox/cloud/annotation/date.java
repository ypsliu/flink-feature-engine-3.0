package com.jffox.cloud.annotation;

import java.lang.annotation.*;

/**
 * @author yanggang
 * @since jdk 1.8
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface date {
    String date() default "2019-01-01";
}
