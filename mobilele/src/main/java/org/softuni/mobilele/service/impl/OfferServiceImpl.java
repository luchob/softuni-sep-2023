package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public OfferEntity createOffer(CreateOfferDTO createOfferDTO, ModelEntity modelEntity) {
    final OfferEntity newOfferEntity = map(createOfferDTO, modelEntity);

    return offerRepository.saveAndFlush(newOfferEntity);
  }

  private OfferEntity map(CreateOfferDTO createOfferDTO, ModelEntity modelEntity) {

    return new OfferEntity()
            .setModel(modelEntity)
            .setUuid(UUID.randomUUID())
            .setDescription(createOfferDTO.description())
            .setEngine(createOfferDTO.engine())
            .setTransmission(createOfferDTO.transmission())
            .setImageUrl(createOfferDTO.imageUrl())
            .setMileage(createOfferDTO.mileage())
            .setPrice(BigDecimal.valueOf(createOfferDTO.price()))
            .setYear(createOfferDTO.year());
  }

}
