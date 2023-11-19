package org.softuni.mobilele.service.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.softuni.mobilele.service.MonitoringService;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MonitoringAspect {

  private final MonitoringService monitoringService;

  public MonitoringAspect(MonitoringService monitoringService) {
    this.monitoringService = monitoringService;
  }


  @Before("PointCuts.warnIfTimeoutExceeded()")
  public void beforeAdvice(){
    monitoringService.logOfferSearch();
  }

  @Around("PointCuts.warnIfTimeoutExceeded()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    WarnIfExecutionTimeExceeded annotation = getAnnotation(joinPoint);

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    var retval =  joinPoint.proceed();

    stopWatch.stop();

    if (stopWatch.getLastTaskTimeMillis() > annotation.time()) {
      LoggerFactory.getLogger(joinPoint.getTarget().getClass()).
          warn("Method {} ran for {} millis which is more "
              + "than the expected limit of {} millis.",
              joinPoint.getSignature().getName(),
              stopWatch.getLastTaskTimeMillis(),
              annotation.time()
          );
    }

    return retval;
  }

  private static WarnIfExecutionTimeExceeded getAnnotation(ProceedingJoinPoint joinPoint) {

    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

    try {
      return joinPoint
          .getTarget()
          .getClass()
          .getMethod(method.getName(), method.getParameterTypes())
          .getAnnotation(WarnIfExecutionTimeExceeded.class);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

}
