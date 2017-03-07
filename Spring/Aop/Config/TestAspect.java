package com.menghao.ssh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Description: (切面增强类)
 * @Create: 2017/3/7 14:13
 * @Author: Mr.m
 */
public class TestAspect {

    //执行顺序—>1
    public void doBefore(JoinPoint point){
        System.out.println("method:"+point.getTarget().getClass().getName()+"."+point.getSignature().getName()+"before");
    }

    //执行顺序—>4 即使异常也会执行
    public void doAfter(JoinPoint point){
        System.out.println("method:"+point.getTarget().getClass().getName()+"."+point.getSignature().getName()+"after");
    }

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

    //执行顺序—>在遇到异常时才执行
    public void doThrowing(JoinPoint jp, Throwable ex){
        System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }
}
