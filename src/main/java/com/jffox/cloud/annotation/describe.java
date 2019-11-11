package com.jffox.cloud.annotation;

import java.lang.annotation.*;

/**
 * @author yanggang
 * @version 1.0
 * @since jdk 1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface describe {
    String describe() default "类功能描述";
}
