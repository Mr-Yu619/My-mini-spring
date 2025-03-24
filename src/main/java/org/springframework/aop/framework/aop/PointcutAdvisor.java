package org.springframework.aop.framework.aop;

public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
