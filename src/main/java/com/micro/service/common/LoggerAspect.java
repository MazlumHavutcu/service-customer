package com.micro.service.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Around("@annotation(com.micro.service.common.LogElapsedTime)")
    public void before(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        long startTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Elapsed Time: {}",
                (endTime - startTime));
    }
}
