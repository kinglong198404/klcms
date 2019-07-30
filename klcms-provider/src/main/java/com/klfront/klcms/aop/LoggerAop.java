package com.klfront.klcms.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAop {
	/**
	 * 定义切点
	 */
	@Pointcut("execution(* com.klfront.klcms.service.impl.*.*(..))")
	public void serviceMethod() {
	}

	 /**
     * 切面的前置方法 即方法执行前拦截到的方法 记录并输出
     * 在目标方法执行之前的通知
     * @param joinPoint
     */
	@Before("execution(* com.klfront.klcms.service.impl.*.*(..))")
	public void before(JoinPoint joinPoint) {
		Object method = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		List<Object> list = Arrays.asList(joinPoint.getArgs());
		log.info("开始执行"+joinPoint.getTarget()+"的方法："+ methodName);
		log.info("参数："+list);
	}
	
	 /**
     * 切面的后置方法，不管抛不抛异常都会走此方法
     * 在目标方法执行之后的通知
     * @param joinPoint
     */
    @After("serviceMethod()")
    public void afterMethod(JoinPoint joinPoint){
        Object method = joinPoint.getSignature();
        String methodName = joinPoint.getSignature().getName();
        log.info("执行"+methodName+"方法结束"); 
    }
    
    /**
     * 在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 在注解后加入returning
     * @param joinPoint 连接点
     * @param result 返回结果
     */
    @AfterReturning(pointcut = "serviceMethod()",returning="result")
    public void afterReturn(JoinPoint joinPoint,Object result ){
        Object method = joinPoint.getSignature();
        String methodName = joinPoint.getSignature().getName();
        log.info("执行"+methodName+"方法正常执行结束,返回结果："+result); 
    }
    

	@AfterThrowing(throwing = "ex", pointcut = "serviceMethod()")
	public void afterThrowing(JoinPoint joinPoint,Throwable ex) {
		Object method = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		log.info("执行"+methodName+"方法出现异常:" + ex.getMessage());
	}
}