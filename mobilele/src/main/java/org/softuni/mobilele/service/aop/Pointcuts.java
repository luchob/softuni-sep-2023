package org.softuni.mobilele.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
  @Pointcut("execution(* org.softuni.mobilele.service.OfferService.getAllOffers(..))")
  public void trackOfferSearch(){}

  @Pointcut("@annotation(WarnIfExecutionExceeds)")
  public void warnIfExecutionExceeds(){}
}
