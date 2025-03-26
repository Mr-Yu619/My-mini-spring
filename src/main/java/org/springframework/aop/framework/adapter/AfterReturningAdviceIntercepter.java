package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;

public class AfterReturningAdviceIntercepter implements MethodInterceptor, AfterAdvice {
    private AfterReturningAdvice advice;

    public AfterReturningAdviceIntercepter(){
    }

    public AfterReturningAdviceIntercepter(AfterReturningAdvice advice){
        this.advice = advice;
    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object retVal = methodInvocation.proceed();
        this.advice.afterReturning(retVal, methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return retVal;
    }
}
