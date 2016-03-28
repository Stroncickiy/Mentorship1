package com.epam.spring.aspects;

import com.epam.spring.events.LongMethodRunningEvent;
import com.epam.spring.model.MethodExecutionRecord;
import com.epam.spring.service.MethodExecutionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Aspect
public class PerformanceMeasureAspect {

    @Value("${permittedExecutionTime}")
    private long permittedExecutionTime;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MethodExecutionService methodExecutionService;


    @Pointcut("execution(* com.epam.spring.service.UserService.*(..))")
    public void pointcutOnServiceMethods() {

    }

    @Around("pointcutOnServiceMethods()")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        Object result = joinPoint.proceed();

        long endedTime = System.currentTimeMillis();

        MethodExecutionRecord record = new MethodExecutionRecord();
        record.setDuration(Duration.ofMillis(endedTime - startTime));
        record.setExecuted(LocalDateTime.now());
        record.setMethodName(methodName);


        if (endedTime - startTime > permittedExecutionTime) {
            record.setPermittedDurationExceeded(true);
            eventPublisher.publishEvent(new LongMethodRunningEvent(record, this));
        }

        methodExecutionService.register(record);

        return result;


    }


}
