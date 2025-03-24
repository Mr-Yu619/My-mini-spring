package org.springframework.aop.framework.aop;

import org.springframework.aop.framework.aop.framework.AdvisorChainFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdvisedSupport {

    //是否使用cglib代理
    private boolean proxyTargetClass = true;

    private TargetSource targetSource;

    // 用于匹配目标方法，判断是否需要对某个方法应用切面逻辑。
    private MethodMatcher methodMatcher;

    // 用于缓存方法的拦截器链，避免重复计算。键是方法的哈希值，值是拦截器链。
    private transient Map<Integer, List<Object>> methodCache;

    AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();

    // 存储所有的切面advisor
    private List<Advisor> advisors = new ArrayList<>();


}
