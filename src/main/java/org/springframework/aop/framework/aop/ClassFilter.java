package org.springframework.aop.framework.aop;

public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
