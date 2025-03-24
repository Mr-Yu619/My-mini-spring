package org.springframework.aop.framework.aop.framework;

import org.springframework.aop.framework.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

public interface AdvisorChainFactory {

    List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass);
}
