package org.springframework.aop;

public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
    // 这里面还有一个获取advice对象的函数继承过来的
    // advice是通知，定义了具体执行的增强逻辑，例如方法执行前后的操作
    // pointcut是切点，定义了在哪些方法和类上应用加强
}
