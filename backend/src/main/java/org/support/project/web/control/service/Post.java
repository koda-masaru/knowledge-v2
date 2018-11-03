package org.support.project.web.control.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Post {
    String path() default "";
    String subscribeToken() default "csrf";
    String publishToken() default "";
    boolean checkReferer() default true;
    boolean checkCookieToken() default true;
    boolean checkReqToken() default false;
    boolean checkHeaderToken() default false;
}
