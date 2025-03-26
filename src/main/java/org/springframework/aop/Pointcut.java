package org.springframework.aop;

public interface Pointcut {

    // 切点
    // 里面有两个判断器
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
