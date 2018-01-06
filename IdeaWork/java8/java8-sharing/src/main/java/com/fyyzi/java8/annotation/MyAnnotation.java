package com.fyyzi.java8.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * 自定义注解（若忘了源注解 可以随便来一个认识的注解，然后把上面的东西搞过来）<br>
 *     @Target 作用范围
 *     @Retention 生命周期
 *     @Repeatable 可重复的，参数传注解的容器类（注解容器类需要有 MyAnnotation[] value();）
 *
 * @author 息阳
 * Create in 22:08 2018/1/5
 * @version 1.0
 */
@Repeatable(MyAnnotations.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "www.fyyzi";
}
