package com.jffox.cloud.examples.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Table {
    /**
     * 数据表名称注解，默认值为类的名称
     *
     * @return
     */
    public String tableName() default "className";
}
