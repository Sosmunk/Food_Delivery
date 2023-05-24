package com.skblab.project.kitchen_service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    @Pointcut("@annotation(LogOrderStatusChange)")
    public void logPointcut() {

    }

    @AfterReturning("logPointcut()")
    public void logOrderStatusChange(JoinPoint joinPoint) {
        log.info("[x] Method " + joinPoint.getSignature().getName() + "changed status of Order with ID: "
                + joinPoint.getArgs()[0] + " [x]");
    }

}
