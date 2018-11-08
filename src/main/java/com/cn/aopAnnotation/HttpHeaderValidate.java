package com.cn.aopAnnotation;

import java.lang.annotation.*;

/**
 * User: cuixiaowei
 * Date: 2018/11/5
 * PackageName: com.cn.aopAnnotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Inherited
public @interface HttpHeaderValidate {
    /**
     * 请求头名称
     */
    String header();
    /**
     * 请求头对应的值
     *//*
    String headerValue() default "";
    *//**
     * 从环境变量或配置文件apiurl.properties获取请求头对应的值
     *//*
    String headerValueEnvName() default "";*/

}
