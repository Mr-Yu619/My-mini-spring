package org.springframework.aop.framework.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import org.springframework.aop.framework.aop.AdvisedSupport;

public class CglibAopProxy implements AopProxy{

    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advised){
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised);
    }
}
