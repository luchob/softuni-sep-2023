package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.ExchangeRatesDTO;

public interface CurrencyService {

  void processExRates(ExchangeRatesDTO exchangeRatesDTO);

}
