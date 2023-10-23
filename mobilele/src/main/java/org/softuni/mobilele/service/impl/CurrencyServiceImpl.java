package org.softuni.mobilele.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.mobilele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.softuni.mobilele.service.CurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

  private final ExchangeRateRepository exchangeRateRepository;

  public CurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
    this.exchangeRateRepository = exchangeRateRepository;
  }

  @Override
  public void refreshRates(ExchangeRatesDTO exchangeRatesDTO) {

    LOGGER.info("Exhange rates received {}", exchangeRatesDTO);

    BigDecimal BGN_TO_USD = getExchangeRate(exchangeRatesDTO, "BGN", "USD").orElse(null);
    BigDecimal BGN_TO_EUR = getExchangeRate(exchangeRatesDTO, "BGN", "EUR").orElse(null);

    if (BGN_TO_USD != null) {
      ExchangeRateEntity exchangeRateEntity =
          new ExchangeRateEntity().setCurrency("USD").setRate(BGN_TO_USD);
      exchangeRateRepository.save(exchangeRateEntity);
    } else {
      LOGGER.error("Unable to get exchange rate for BGN TO USD");
    }

    if (BGN_TO_EUR != null) {
      ExchangeRateEntity exchangeRateEntity =
          new ExchangeRateEntity().setCurrency("EUR").setRate(BGN_TO_EUR);
      exchangeRateRepository.save(exchangeRateEntity);
    } else {
      LOGGER.error("Unable to get exchange rate for BGN TO EUR");
    }

    LOGGER.info("Rates refreshed...");
  }

  private static Optional<BigDecimal> getExchangeRate(
      ExchangeRatesDTO exchangeRatesDTO,
      String from,
      String to
  ) {

    Objects.requireNonNull(from, "From currency cannot be null");
    Objects.requireNonNull(to, "To currency cannot be null");

//    {
//        "base": "USD",
//        "rates": {
//          "BGN": 1.840515,
//          "EUR": 0.937668
//    }

      // e.g. USD -> USD
      if (Objects.equals(from, to)) {
        return Optional.of(BigDecimal.ONE);
      }

      if (from.equals(exchangeRatesDTO.base())) {
        // e.g. USD -> BGN
        if (exchangeRatesDTO.rates().containsKey(to)) {
          return Optional.of(exchangeRatesDTO.rates().get(to));
        }
      } else if (Objects.equals(to, exchangeRatesDTO.base())){
        // e.g. BGN -> USD
        if (exchangeRatesDTO.rates().containsKey(from)) {
          return Optional.of(BigDecimal.ONE.divide(
              exchangeRatesDTO.rates().get(from),
              3,
              RoundingMode.DOWN
          ));
        }
      } else if (exchangeRatesDTO.rates().containsKey(from) &&
          exchangeRatesDTO.rates().containsKey(to)) {
        return Optional.of(
            exchangeRatesDTO.rates().get(to)
                .divide(exchangeRatesDTO.rates().get(from),
                    3, RoundingMode.DOWN)
        );
      }

      return Optional.empty();
    }
}
