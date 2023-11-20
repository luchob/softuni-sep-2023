package org.softuni.mobilele.testutils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;
import org.softuni.mobilele.model.enums.UserRoleEnum;
import org.softuni.mobilele.repository.BrandRepository;
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

  @Autowired
  private BrandRepository brandRepository;

  public void createExchangeRate(String currency, BigDecimal rate) {
    exchangeRateRepository.save(
        new ExchangeRateEntity().setCurrency(currency).setRate(rate)
    );
  }


  public OfferEntity createTestOffer(UserEntity owner) {

    // create test brand
    BrandEntity brandEntity = brandRepository.save(new BrandEntity()
        .setName("Test Brand")
        .setModels(List.of(
            new ModelEntity().setName("Test Model"),
            new ModelEntity().setName("Test Model1")
        )));

    // create test offer
    OfferEntity offer = new OfferEntity()
        .setModel(brandEntity.getModels().get(0))
        .setImageUrl("https://www.google.com")
        .setPrice(BigDecimal.valueOf(1000))
        .setYear(2020)
        .setUuid(UUID.randomUUID())
        .setDescription("Test Description")
        .setEngine(EngineEnum.PETROL)
        .setMileage(10000)
        .setTransmission(TransmissionEnum.MANUAL)
        .setSeller(owner);

    return offerRepository.save(offer);
  }
//
  public void cleanUp() {
    exchangeRateRepository.deleteAll();
    offerRepository.deleteAll();
    brandRepository.deleteAll();
  }
}
