package com.menghao.ssh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description: (基于注解的aop增强)
 * @Create: 2017/3/7 15:41
 * @Author: Mr.m
 */
@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(* com.menghao.ssh.service.*.*(..))")
    private void pointCutMethod() {
       //这里的代码不会执行
    }

    //前置通知 执行顺序—>1
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint point){
        System.out.println("method:"+point.getTarget().getClass().getName()+"."+point.getSignature().getName()+"before");
    }

    //后置通知 执行顺序—>4 即使异常也会执行
    @After("pointCutMethod()")
    public void doAfter(JoinPoint point){
        System.out.println("method:"+point.getTarget().getClass().getName()+"."+point.getSignature().getName()+"after");
    }

    //环绕通知
    @Around("pointCutMethod()")
    public void doAround(ProceedingJoinPoint pPoint) throws Throwable {
        long time = System.currentTimeMillis();
        //执行顺序—>2
        System.out.println("method:"+pPoint.getTarget().getClass().getName()+"."+pPoint.getSignature().getName()+"around->before");
        Object retVal = pPoint.proceed();
        System.out.println("method return:"+retVal);
        time = System.currentTimeMillis() - time;
        //执行顺序—>3 在遇到异常时直接跳过执行doThrowing
        System.out.println("method:"+pPoint.getTarget().getClass().getName()+"."+pPoint.getSignature().getName()+"around->after");
        System.out.println("process time: " + time + " ms");
    }

    //异常通知 执行顺序—>在遇到异常时才执行
    @AfterThrowing(pointcut ="pointCutMethod()",throwing = "ex")
    public void doThrowing(JoinPoint jp, Throwable ex){
        System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }

    //声明后置通知->5 只有在有返回值时才执行
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        System.out.println("返回通知");
    }
}
