package com.example.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTestController {
    private static final Logger logger = LoggerFactory.getLogger(AopTestController.class);
    /**
     * 定义一个切点
     */
    @Pointcut(value = "execution(public String test(..))")
    public void cutOffPoint(){
    }
    @Before("cutOffPoint()")
    public void beforeTest(){
        logger.info("beforeTest");
    }
    @After("cutOffPoint()")
    public void afterTest(){
        logger.info("afterTest");
    }
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    @Around("cutOffPoint()")
    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint){
        startTime.set(System.currentTimeMillis());
        logger.info("环绕通知执行");
        Object obj;
        try {
            obj = proceedingJoinPoint.proceed();
            logger.info("执行返回值:"+obj);
            logger.info(proceedingJoinPoint.getSignature().getName()+"方法执行耗时:"+(System.currentTimeMillis()-startTime.get()));
        } catch (Throwable throwable) {
            obj = throwable.toString();
        }
        return obj;

    }
    @AfterReturning(returning = "result",pointcut = "cutOffPoint()")
    public void doAfterReturning(Object result)throws Throwable{
        logger.info("AfterReturning,都执行完了,上场");

    }
    @AfterThrowing(throwing = "e",pointcut = "cutOffPoint()")
    public void doAfterReturning(Throwable e){
        logger.info("AfterThrowing,这个是在切入执行报错的时候执行的");
        logger.info("错误的信息"+e.getMessage());
    }


}




