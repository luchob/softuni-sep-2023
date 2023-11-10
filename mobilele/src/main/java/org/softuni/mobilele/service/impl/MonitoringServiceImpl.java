package org.softuni.mobilele.service.impl;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.softuni.mobilele.service.MonitoringService;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {

  private final MeterRegistry meterRegistry;

  private final Counter offerSearches;

  public MonitoringServiceImpl(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;

    offerSearches = Counter
        .builder("offers_search_cnt")
        .description("How much offer searches are performed")
        .tags("offer", "performance")
        .register(meterRegistry);
  }

  @Override
  public void incOfferSearches() {
    offerSearches.increment();
  }
}
