package org.springframework.aop;

import org.springframework.aop.framework.AdvisorChainFactory;
import org.springframework.aop.framework.DefaultAdvisorChainFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdvisedSupport {

    //是否使用cglib代理
    private boolean proxyTargetClass = true;

    // 能捕获一个类的所有实现接口的东西
    private TargetSource targetSource;

    // 用于匹配目标方法，判断是否需要对某个方法应用切面逻辑。
    private MethodMatcher methodMatcher;

    // 用于缓存方法的拦截器链，避免重复计算。键是方法的哈希值，值是拦截器链。
    private transient Map<Integer, List<Object>> methodCache;

    AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();

    // 存储所有的切面advisor
    private List<Advisor> advisors = new ArrayList<>();

    public AdvisedSupport(){
        this.methodCache = new ConcurrentHashMap<>(32);
    }



    public boolean isProxyTargetClass(){
        return proxyTargetClass;
    }
    public void setProxyTargetClass(boolean proxyTargetClass){
        this.proxyTargetClass = proxyTargetClass;
    }


    public void addAdvisor(Advisor advisor){
        advisors.add(advisor);
    }
    public List<Advisor> getAdvisors(){
        return advisors;
    }


    public TargetSource getTargetSource(){
        return targetSource;
    }
    public void setTargetSource(TargetSource targetSource){
        this.targetSource = targetSource;
    }


    public MethodMatcher getMethodMatcher(){
        return methodMatcher;
    }
    public void setMethodMatcher(MethodMatcher methodMatcher){
        this.methodMatcher = methodMatcher;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method,Class<?> targetClass){
        Integer cacheKey = method.hashCode();
        List<Object> cached = this.methodCache.get(cacheKey);
        if(cached == null){
            cached = this.advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(
                    this, method, targetClass
            );
            this.methodCache.put(cacheKey, cached);
        }
        return cached;
    }
}
