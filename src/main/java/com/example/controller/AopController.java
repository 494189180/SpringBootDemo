package com.example.controller;

import com.example.page.PageResultBean;
import com.example.page.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *注释描述 
 *@Description 处理全局异常
 *@Author gaoyanbao
 *@Date 2019/1/20 17:55
 *@modified by:
**/

@Aspect
@Component
public class AopController {
    private static final Logger logger = LoggerFactory.getLogger(AopController.class);
    ThreadLocal<ResultBean> resultBeanThreadLocal = new ThreadLocal<>();
    ThreadLocal<PageResultBean> pageResultBeanThreadLocal = new ThreadLocal<>();
    ThreadLocal<Long> start = new ThreadLocal<>();
    /**
     * 定义一个切点
     */
    @Pointcut(value = "execution(public com.example.page.PageResultBean *(..))")
    public void handlerPageResultBeanMethod(){
    }
    @Pointcut(value = "execution(public com.example.page.ResultBean *(..))")
    public void handlerResultBeanMethod(){
    }
    @Around("handlerPageResultBeanMethod()")
    public Object handlerPageResultBeanMethod(ProceedingJoinPoint pjp){
        start.set(System.currentTimeMillis());
        try {
            pageResultBeanThreadLocal.set((PageResultBean<?>)pjp.proceed());
            logger.info(pjp.getSignature()+"方法page执行耗时:"+(System.currentTimeMillis()-start.get()));
        } catch (Throwable e) {
            ResultBean<?> resultBean = handlerException(pjp, e);
            pageResultBeanThreadLocal.set(new PageResultBean<>().setMsg(resultBean.getMsg()).setCode(resultBean.getCode()));
        }
        return pageResultBeanThreadLocal.get();
    }
    @Around("handlerResultBeanMethod()")
    public Object handlerResultBeanMethod(ProceedingJoinPoint pjp){
        start.set(System.currentTimeMillis());
        try {
            resultBeanThreadLocal.set((ResultBean<?>) pjp.proceed());
            logger.info(pjp.getSignature()+"方法执行耗时:"+(System.currentTimeMillis()-start.get()));
        } catch (Throwable e) {
            resultBeanThreadLocal.set(handlerException(pjp,e));
        }
        return resultBeanThreadLocal.get();
    }
    /**
     * 封装异常信息,注意区分已知异常和未知异常
     */
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp,Throwable e){
        ResultBean<?> result = new PageResultBean();
        logger.error(pjp.getSignature()+"error",e);
//        已知异常
//        if(e instanceof CheckException){
//            result.setMsg(e.getLocalizedMessage());
//            result.setCode(ResultBean.FAIL);
//        }else if(e instanceof UnloginException){
//            result.setMsg("Unlogin");
//            result.setCode(ResultBean.FAIL);
//        }else {
//            result.setMsg(e.toString());
//            result.setCode(ResultBean.FAIL);
//        }
        result.setMsg(e.toString());
        result.setCode(ResultBean.FAIL);
        return result;
    }



}















