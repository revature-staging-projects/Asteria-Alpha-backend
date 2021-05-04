package com.revature.aspects.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LogginAspect {

    private final Logger logger = LogManager.getLogger(LogginAspect.class);

    @Pointcut("within(com.revature..*) && !within(com.revature.filters..*)")
    public void logAllPointcut() {}

    private String extractMethodSignature(final JoinPoint jp){
        return jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
    }

    @AfterThrowing(pointcut = "logAllPointcut()", throwing = "e")
    public void logErrorOccurrence(final JoinPoint jp,final Exception e) {
        final String methodSig = extractMethodSignature(jp);
        logger.error("{} was thrown in method {} at {} with message: {}", e.getClass().getSimpleName(), methodSig, LocalDateTime.now(), e.getMessage());
    }

}
