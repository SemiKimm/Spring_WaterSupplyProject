package com.nhnacademy.edu.springframework.project.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * LoggingAspect.
 */
@Aspect
@Component
public class LoggingAspect {
    private final Log log = LogFactory.getLog(LoggingAspect.class);

    /**
     * 메서드별 실행시간에 대한 log 를 남깁니다.
     */
    @Around("execution(* *(..))")
    public Object logTimer(ProceedingJoinPoint ptj) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start(ptj.getSignature().getName());
            return ptj.proceed();
        } finally {
            stopWatch.stop();
            log.info(ptj.getSignature().getName() + " : " + stopWatch.prettyPrint());
        }
    }
}
