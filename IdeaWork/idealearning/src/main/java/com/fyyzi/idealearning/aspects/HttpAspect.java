package com.fyyzi.idealearning.aspects;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 息阳
 * 2017/12/29 16:29
 * @version 1.0
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.fyyzi.idealearning.jpa.controller.GirlController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // url
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL{}", request.getRequestURL());

        // method
        logger.info("Method{}",request.getMethod());

        // ip
        logger.info("IP{}",request.getRemoteAddr());

        // 类方法

        String class_name = joinPoint.getSignature().getDeclaringTypeName();
        String class_method_name = joinPoint.getSignature().getName();

        logger.info("class_method{}",class_name+"."+class_method_name);

        // 参数
        logger.info("a{}",joinPoint.getArgs());
    }

    @After("log()")
    public void after() {
        logger.info("22222222222222");
    }
}
