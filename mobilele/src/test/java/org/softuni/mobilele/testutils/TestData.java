package org.softuni.mobilele.testutils;

import java.math.BigDecimal;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestData {

  @Autowired
  private ExchangeRateRepository exchangeRateRepository;

  public void createExchangeRate(String currency, BigDecimal rate) {
    exchangeRateRepository.save(
        new ExchangeRateEntity().setCurrency(currency).setRate(rate)
    );
  }

  public void cleanAllTestData() {
    exchangeRateRepository.deleteAll();
  }
}
