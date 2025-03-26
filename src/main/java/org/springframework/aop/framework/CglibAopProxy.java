package org.springframework.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

public class CglibAopProxy implements AopProxy{

    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advised){
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        //Enhancer 是 CGLIB 库中的一个类，用于生成动态代理。
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        //设置代理类需要实现的接口
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedIntercepter(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedIntercepter implements MethodInterceptor{

        private final AdvisedSupport advised;

        private DynamicAdvisedIntercepter(AdvisedSupport advised){this.advised = advised;}


        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            Object target = advised.getTargetSource().getTarget();
            Class<?> targetClass = target.getClass();
            Object retVal = null;
            // 获取方法和类对应的拦截器链
            List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy);
            if(chain == null || chain.isEmpty()){
                retVal = methodProxy.invoke(target, args);
            } else {
                retVal = methodInvocation.proceed();
            }
            return retVal;
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object proxy, Object target, Method method,
                 Object[] arguments, Class<?> targetClass,
                 List<Object> interceptorsAndDynamicMethodMatchers, MethodProxy methodProxy){
            super(proxy, target, method, arguments, targetClass, interceptorsAndDynamicMethodMatchers);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return super.proceed();
        }
    }
}
