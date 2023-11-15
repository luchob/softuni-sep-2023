package org.softuni.mobilele.service.impl;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.softuni.mobilele.service.MonitoringService;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {

  private final Counter offerSearches;

  public MonitoringServiceImpl(MeterRegistry meterRegistry) {
    offerSearches = Counter
        .builder("offer_search_cnt")
        .description("How many offer searched we have performed")
        .register(meterRegistry);
  }

  @Override
  public void logOfferSearch() {
    offerSearches.increment();
  }
}
