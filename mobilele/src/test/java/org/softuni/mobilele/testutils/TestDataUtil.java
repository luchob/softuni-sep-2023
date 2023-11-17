package org.softuni.mobilele.testutils;

import java.math.BigDecimal;
import java.util.List;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.enums.UserRoleEnum;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtil {

  @Autowired
  private ExchangeRateRepository exchangeRateRepository;

  @Autowired
  private OfferRepository offerRepository;

  public void createExchangeRate(String currency, BigDecimal rate) {
    exchangeRateRepository.save(
        new ExchangeRateEntity().setCurrency(currency).setRate(rate)
    );
  }

  public String createTestOffer(UserEntity owner) {
    return null;
  }

  public void cleanAllTestData() {
    exchangeRateRepository.deleteAll();
  }
}
