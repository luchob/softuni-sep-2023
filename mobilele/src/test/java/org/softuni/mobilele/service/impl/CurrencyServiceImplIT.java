package org.softuni.mobilele.service.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.softuni.mobilele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.softuni.mobilele.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyServiceImplIT {

  @Autowired
  private CurrencyService currencyService;

  @Autowired
  private ExchangeRateRepository exchangeRateRepository;

  @BeforeEach
  void setUp() {
    exchangeRateRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    exchangeRateRepository.deleteAll();
  }

  @ParameterizedTest(name = "Convering BGN TO USD, ex rate {0}, expected {1}.")
  @MethodSource("testData_BGN_TO_USD")
  void test_BGN_TO_USD(Double exchangeRate, Double expected) {

    //    "base": "USD",
//    "rates": {
//    "BGN": 1.840515,
//    "EUR": 0.937668
//    }
    var exRates = new ExchangeRatesDTO("USD", Map.of("BGN", BigDecimal.valueOf(exchangeRate)));

    currencyService.refreshRates(exRates);

    BigDecimal actualRate = exchangeRateRepository
        .findById("USD")
        .map(ExchangeRateEntity::getRate)
        .orElseThrow();

    Assertions.assertEquals(expected, actualRate.doubleValue(), 0.001);
  }

  private static Stream<Arguments> testData_BGN_TO_USD() {
    return Stream.of(
        Arguments.of(1.840515, 0.54),
        Arguments.of(1.715515, 0.58),
        Arguments.of(1.0, 1.0)

    );
  }

}
