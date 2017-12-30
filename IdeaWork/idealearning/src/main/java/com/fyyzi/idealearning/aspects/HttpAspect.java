package com.fyyzi.idealearning.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
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

    /**
     * 配置通用切面方法  在使用的时候可以直接用该方法，而不必每次都要输入长长的参数
     */
    @Pointcut("execution(public * com.fyyzi.idealearning.jpa.controller.GirlController.*(..))")
    public void log() {
    }

    /**
     * 方法之前
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // url
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL：{}", request.getRequestURL());

        // method
        logger.info("Method：{}",request.getMethod());

        // ip
        logger.info("IP：{}",request.getRemoteAddr());

        // 类方法

        String class_name = joinPoint.getSignature().getDeclaringTypeName();
        String class_method_name = joinPoint.getSignature().getName();

        logger.info("class_method：{}",class_name+"."+class_method_name);

        // 参数
        logger.info("参数：{}",joinPoint.getArgs());
    }

    /**
     * 方法之后
     */
    @After("log()")
    public void doAfter() {
        logger.info("22222222222222");
    }

    /**
     * 获取返回值
     * @param object
     */
    @AfterReturning(pointcut = "log()",returning = "object")
    public void doAfrerReturning(Object object){
        logger.info("返回值：{}",object);
    }
}
