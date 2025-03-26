package org.springframework.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    // 判断一个方法是否满足特定的条件，条件自己定
    boolean matches(Method method, Class<?> targetClass);
}
