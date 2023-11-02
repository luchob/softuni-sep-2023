package org.softuni.mobilele.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.mobilele.model.dto.ConvertRequestDTO;
import org.softuni.mobilele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilele.model.dto.MoneyDTO;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.softuni.mobilele.service.CurrencyService;
import org.softuni.mobilele.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

  private static final String BASE_CURRENCY = "BGN";

  private final ExchangeRateRepository exchangeRateRepository;

  public CurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
    this.exchangeRateRepository = exchangeRateRepository;
  }

  @Override
  public void refreshRates(ExchangeRatesDTO exchangeRatesDTO) {

    LOGGER.info("Exhange rates received {}", exchangeRatesDTO);

    BigDecimal BGN_TO_USD = getExchangeRate(exchangeRatesDTO, "USD").orElse(null);
    BigDecimal BGN_TO_EUR = getExchangeRate(exchangeRatesDTO, "EUR").orElse(null);

    if (BGN_TO_USD != null) {
      ExchangeRateEntity exchangeRateEntity =
          new ExchangeRateEntity().setCurrency("USD").setRate(BGN_TO_USD);
      exchangeRateRepository.save(exchangeRateEntity);
    } else {
      LOGGER.error("Unable to get exchange rate for {} TO USD", BASE_CURRENCY);
    }

    if (BGN_TO_EUR != null) {
      ExchangeRateEntity exchangeRateEntity =
          new ExchangeRateEntity().setCurrency("EUR").setRate(BGN_TO_EUR);
      exchangeRateRepository.save(exchangeRateEntity);
    } else {
      LOGGER.error("Unable to get exchange rate for {} TO EUR", BASE_CURRENCY);
    }

    LOGGER.info("Rates refreshed...");
  }

  private static Optional<BigDecimal> getExchangeRate(
      ExchangeRatesDTO exchangeRatesDTO,
      String to
  ) {

    Objects.requireNonNull(CurrencyServiceImpl.BASE_CURRENCY, "From currency cannot be null");
    Objects.requireNonNull(to, "To currency cannot be null");

//    {
//        "base": "USD",
//        "rates": {
//          "BGN": 1.840515,
//          "EUR": 0.937668
//    }

      // e.g. USD -> USD
      if (Objects.equals(CurrencyServiceImpl.BASE_CURRENCY, to)) {
        return Optional.of(BigDecimal.ONE);
      }

      if (CurrencyServiceImpl.BASE_CURRENCY.equals(exchangeRatesDTO.base())) {
        // e.g. USD -> BGN
        if (exchangeRatesDTO.rates().containsKey(to)) {
          return Optional.of(exchangeRatesDTO.rates().get(to));
        }
      } else if (Objects.equals(to, exchangeRatesDTO.base())){
        // e.g. BGN -> USD
        if (exchangeRatesDTO.rates().containsKey(CurrencyServiceImpl.BASE_CURRENCY)) {
          return Optional.of(BigDecimal.ONE.divide(
              exchangeRatesDTO.rates().get(CurrencyServiceImpl.BASE_CURRENCY),
              3,
              RoundingMode.DOWN
          ));
        }
      } else if (exchangeRatesDTO.rates().containsKey(CurrencyServiceImpl.BASE_CURRENCY) &&
          exchangeRatesDTO.rates().containsKey(to)) {
        return Optional.of(
            exchangeRatesDTO.rates().get(to)
                .divide(exchangeRatesDTO.rates().get(CurrencyServiceImpl.BASE_CURRENCY),
                    3, RoundingMode.DOWN)
        );
      }

      return Optional.empty();
    }

  @Override
  public MoneyDTO convertBase(ConvertRequestDTO convertRequestDTO) {
    var exRate = exchangeRateRepository
        .findById(convertRequestDTO.target())
        .map(ExchangeRateEntity::getRate)
        .orElseThrow(() -> new ObjectNotFoundException(
            "Exchange rate for " + convertRequestDTO.target() + " not found!",
            convertRequestDTO.target())
        );

    return new MoneyDTO(BASE_CURRENCY, convertRequestDTO.amount().multiply(exRate));
  }
}
