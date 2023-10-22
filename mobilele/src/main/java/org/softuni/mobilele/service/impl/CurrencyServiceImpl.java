package org.softuni.mobilele.service.impl;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;
import org.softuni.mobilele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.softuni.mobilele.service.CurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

  private final String BASE_CURRENCY = "BGN";
  private final ExchangeRateRepository exchangeRateRepository;

  public CurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
    this.exchangeRateRepository = exchangeRateRepository;
  }

  @Override
  public void processExRates(ExchangeRatesDTO exchangeRatesDTO) {
    var BGN_to_USD = getExchangeRate(exchangeRatesDTO, "USD").orElseThrow();
    var BGN_to_EUR = getExchangeRate(exchangeRatesDTO, "EUR").orElseThrow();

    ExchangeRateEntity bgnToUsd = new ExchangeRateEntity().setCurrency("USD").setRate(BGN_to_USD);
    ExchangeRateEntity bgnToEur = new ExchangeRateEntity().setCurrency("EUR").setRate(BGN_to_EUR);

    exchangeRateRepository.save(bgnToUsd);
    exchangeRateRepository.save(bgnToEur);
  }

  private Optional<BigDecimal> getExchangeRate(
      ExchangeRatesDTO exchangeRatesDTO,
      String to) {

    // e.g. base=USD, rates={BGN=1.84875, EUR=0.945725}

    if (Objects.equals(to, BASE_CURRENCY)) {
      return Optional.of(BigDecimal.ONE);
    }

    if (BASE_CURRENCY.equals(exchangeRatesDTO.base())) {
      // e.g. USD -> BGN
      return Optional.ofNullable(exchangeRatesDTO.rates().get(to));
    } else if(to.equals(exchangeRatesDTO.base())) {
      // e.g. BGN -> USD
      if (exchangeRatesDTO.rates().containsKey(BASE_CURRENCY)) {
        return Optional.of(BigDecimal.ONE.divide(exchangeRatesDTO.rates().get(BASE_CURRENCY), 3, RoundingMode.HALF_DOWN));
      }
    } else if(exchangeRatesDTO.rates().containsKey(BASE_CURRENCY) && exchangeRatesDTO.rates().containsKey(to)) {

      // e.g. (from) BGN -> (to) EUR
      // USD/BGN = 1.84875
      // USD/EUR = 0.945725

      // USD/EUR
      // -------= USD/EUR * BGN/USD = BGN/EUR = 0.511
      // USD/BGN

      return Optional.of(exchangeRatesDTO.rates().get(to).divide(exchangeRatesDTO.rates().get(BASE_CURRENCY), 3, RoundingMode.HALF_DOWN));
    }

    return Optional.empty();
  }
}
