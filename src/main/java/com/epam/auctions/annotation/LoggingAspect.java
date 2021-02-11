package com.epam.auctions.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(DbAspect.class);

    @Pointcut("execution(* com.epam.auctions.service.impl.*.*(..))")
    public void loggingMethods() {/* Empty */}

    @Before("loggingMethods()")
    public void procede(JoinPoint call) {
        log.info("Calling {} method", call.getSignature().getName());
    }
}
