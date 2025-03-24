package org.springframework.aop.framework.aop;

import java.lang.reflect.Method;

public interface MethodBeforeAdvic extends BeforeAdvice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
