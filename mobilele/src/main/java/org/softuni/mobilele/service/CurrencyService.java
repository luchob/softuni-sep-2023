package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.ConvertRequestDTO;
import org.softuni.mobilele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilele.model.dto.MoneyDTO;

public interface CurrencyService {

  void refreshRates(ExchangeRatesDTO exchangeRatesDTO);

  MoneyDTO convertBase(ConvertRequestDTO moneyDTO);
}
