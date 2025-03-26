package org.springframework.aop;

public interface ClassFilter {
    // 传入一个Class对象，条件自己定
    boolean matches(Class<?> clazz);
}
