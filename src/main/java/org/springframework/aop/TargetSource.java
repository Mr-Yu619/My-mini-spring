package org.springframework.aop;

// 这是一个能获取一个对象所有实现接口的类
public class TargetSource {

    // 这个实例一旦创建就不可以改变，这样做线程安全
    private final Object target;

    public TargetSource(Object target){
        this.target = target;
    }
    // 获取target对象所实现的所有接口，并将这写接口的Class对象返回
    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }
    public Object getTarget(){
        return this.target;
    }
}
