package com.spring.development.common.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.spring.development.module.*.controller.*.*(..)) || execution(public * com.spring.development.module.*.*(..))")
    public void log(){}

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) {

//        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        try {
            long startTimeMillis = System.currentTimeMillis();
            //调用 proceed() 方法才会真正的执行实际被代理的方法
            Object result = joinPoint.proceed();

            String input = Arrays.toString(joinPoint.getArgs());

            String ouput = JSON.toJSONString(result);

            long consultTime = System.currentTimeMillis() - startTimeMillis;
            logger.info("请求接口路径: "+ joinPoint);
            logger.info("入参:" + input);
            logger.info("出参:" + ouput);
            logger.info("执行时间: " + consultTime + " 毫秒");

            return result;
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(),throwable);
            throw new RuntimeException(throwable.getMessage());
        }
    }
}
