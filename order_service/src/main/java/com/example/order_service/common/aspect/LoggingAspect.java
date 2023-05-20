package com.example.order_service.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Profile("dev")
public class LoggingAspect {
    @Before(value = "@annotation(com.example.order_service.common.annotation.LogMethodExecution)")
    public void logMethodCall(JoinPoint joinPoint) {
        log.info("Вызван метод {}", joinPoint.getSignature().getName());
    }
}
