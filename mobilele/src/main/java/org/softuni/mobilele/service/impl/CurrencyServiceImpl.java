package org.softuni.mobilele.service.impl;

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

  private final ExchangeRateRepository exchangeRateRepository;

  public CurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
    this.exchangeRateRepository = exchangeRateRepository;
  }

  @Override
  public void processExRates(ExchangeRatesDTO exchangeRatesDTO) {
    var BGN_to_USD = getExchangeRate(exchangeRatesDTO, "BGN", "USD").orElseThrow();
    var BGN_to_EUR = getExchangeRate(exchangeRatesDTO, "BGN", "EUR").orElseThrow();

    ExchangeRateEntity bgnToUsd = new ExchangeRateEntity().setCurrency("USD").setRate(BGN_to_USD);
    ExchangeRateEntity bgnToEur = new ExchangeRateEntity().setCurrency("EUR").setRate(BGN_to_EUR);

    exchangeRateRepository.save(bgnToUsd);
    exchangeRateRepository.save(bgnToEur);
  }

  private Optional<BigDecimal> getExchangeRate(ExchangeRatesDTO exchangeRatesDTO,
      String from, String to) {

    if (Objects.equals(to, from)) {
      return Optional.of(BigDecimal.ONE);
    }

    if (from.equals(exchangeRatesDTO.base())) {
      return Optional.ofNullable(exchangeRatesDTO.rates().get(to));
    } else if (to.equals(exchangeRatesDTO.base())) {
      if (exchangeRatesDTO.rates().containsKey(from)) {
        return Optional.of(
            BigDecimal.ONE.divide(exchangeRatesDTO.rates().get(from), 3, RoundingMode.HALF_DOWN));
      }
    } else if (exchangeRatesDTO.rates().containsKey(from) && exchangeRatesDTO.rates()
        .containsKey(to)) {
      return Optional.of(exchangeRatesDTO.rates().get(to)
          .divide(exchangeRatesDTO.rates().get(from), 3, RoundingMode.HALF_DOWN));
    }

    return Optional.empty();
  }
}