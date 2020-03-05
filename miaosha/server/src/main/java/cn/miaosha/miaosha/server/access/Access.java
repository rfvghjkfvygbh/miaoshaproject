package cn.miaosha.miaosha.server.access;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Retention(RUNTIME)
@Target(METHOD)
public @interface Access {
int expireSeconds();
int count();
boolean needLogin() default true;
}
