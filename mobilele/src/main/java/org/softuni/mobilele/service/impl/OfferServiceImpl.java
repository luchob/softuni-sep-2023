package org.softuni.mobilele.service.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.dto.OfferDetailsDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final ModelRepository modelRepository;

  public OfferServiceImpl(OfferRepository offerRepository,
      ModelRepository modelRepository) {
    this.offerRepository = offerRepository;
    this.modelRepository = modelRepository;
  }

  @Override
  public UUID createOffer(CreateOfferDTO createOfferDTO) {

    OfferEntity newOffer = map(createOfferDTO);
    ModelEntity modelEntity = modelRepository.findById(createOfferDTO.modelId()).orElseThrow(() ->
        new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found!"));

    newOffer.setModel(modelEntity);

    newOffer = offerRepository.save(newOffer);

    return newOffer.getUuid();
  }

  private OfferEntity map(CreateOfferDTO createOfferDTO) {
    return new OfferEntity()
        .setUuid(UUID.randomUUID())
        .setDescription(createOfferDTO.description())
        .setEngine(createOfferDTO.engine())
        .setTransmission(createOfferDTO.transmission())
        .setImageUrl(createOfferDTO.imageUrl())
        .setMileage(createOfferDTO.mileage())
        .setPrice(BigDecimal.valueOf(createOfferDTO.price()))
        .setYear(createOfferDTO.year());
  }

  public Page<OfferDetailsDTO> getAllOffers(Pageable pageable) {
    return
        offerRepository.
            findAll(pageable).
            map(this::map);
  }

  private OfferDetailsDTO map(OfferEntity offerEntity) {

    return new OfferDetailsDTO(
          offerEntity.getDescription(),
          offerEntity.getUuid(),
          offerEntity.getEngine(),
          offerEntity.getImageUrl(),
          offerEntity.getMileage(),
          offerEntity.getPrice(),
          offerEntity.getTransmission(),
          offerEntity.getYear(),
          offerEntity.getModel().getName(),
          offerEntity.getModel().getBrand().getName());
  }

}
