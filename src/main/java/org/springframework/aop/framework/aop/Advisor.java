package org.springframework.aop.framework.aop;

import org.aopalliance.aop.Advice;

public interface Advisor {

    Advice getAdvice();
}
