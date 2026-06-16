package com.example.student_api.security;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
        private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
     @Around("@annotation(com.example.student_api.security.LogAPI)")
     public Object logAPI(ProceedingJoinPoint joinPoint) throws Throwable
     {
          logger.info("API Called: {}",
            joinPoint.getSignature().getName());
          Object result = joinPoint.proceed();
          logger.info("Response : Success ");
          return result;
     }
    }
