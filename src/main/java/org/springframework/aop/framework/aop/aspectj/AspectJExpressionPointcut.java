package org.springframework.aop.framework.aop.aspectj;

import net.sf.cglib.transform.ClassFilter;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.aop.MethodMatcher;

public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

}
