package org.softuni.mobilele.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

  @Pointcut("@annotation(WarnIfExecutionTimeExceeded)")
  public void warnIfTimeoutExceeded(){}

  @Pointcut("execution(* org.softuni.mobilele.service.OfferService.getAllOffers(..))")
  public void track(){}

}
