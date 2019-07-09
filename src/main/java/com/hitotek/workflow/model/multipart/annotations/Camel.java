package com.hitotek.workflow.model.multipart.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description Mybatis 返回数据后直接处理为驼峰Key
 * @date 2019-03-02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Camel {
}
