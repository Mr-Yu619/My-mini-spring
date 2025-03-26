package org.springframework.aop.framework;


import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.PointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultAdvisorChainFactory implements AdvisorChainFactory{

    // 从配置类中根据目标方法和目标类筛选出拦截器
    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass) {
        Advisor[] advisors = config.getAdvisors().toArray(new Advisor[0]);
        List<Object> interceptorList = new ArrayList<>(advisors.length);
        Class<?> actualClass = (targetClass != null ? targetClass : method.getDeclaringClass());
        for (Advisor advisor : advisors){
            if (advisor instanceof PointcutAdvisor){
                PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;
                if(pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass)){
                    MethodMatcher mm = pointcutAdvisor.getPointcut().getMethodMatcher();
                    boolean match;
                    match = mm.matches(method, actualClass);
                    if(match){
                        MethodInterceptor interceptor = (MethodInterceptor) advisor.getAdvice();
                        interceptorList.add(interceptor);
                    }
                }
            }
        }
        return interceptorList;
    }
}
