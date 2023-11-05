package org.softuni.mobilele.service.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Optional;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.dto.OfferDetailDTO;
import org.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.softuni.mobilele.model.dto.SearchOfferDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.repository.OfferSpecification;
import org.softuni.mobilele.service.OfferService;
import org.softuni.mobilele.service.exception.ObjectNotFoundException;
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

  @Override
  public Page<OfferSummaryDTO> getAllOffers(SearchOfferDTO searchOfferDTO,
      Pageable pageable) {

    OfferSpecification offerSpec = new OfferSpecification(searchOfferDTO);

    return offerRepository
        .findAll(offerSpec, pageable)
        .map(OfferServiceImpl::mapAsSummary);
  }

  @Override
  public Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID) {
    return offerRepository
        .findByUuid(offerUUID)
        .map(OfferServiceImpl::mapAsDetails);
  }

  @Override
  @Transactional
  public void deleteOffer(UUID offerUUID) {
    offerRepository.deleteByUuid(offerUUID);
  }

  private static OfferDetailDTO mapAsDetails(OfferEntity offerEntity) {
    // TODO: reuse
    return new OfferDetailDTO(
        offerEntity.getUuid().toString(),
        offerEntity.getModel().getBrand().getName(),
        offerEntity.getModel().getName(),
        offerEntity.getYear(),
        offerEntity.getMileage(),
        offerEntity.getPrice(),
        offerEntity.getEngine(),
        offerEntity.getTransmission(),
        offerEntity.getImageUrl());
  }


  private static OfferSummaryDTO mapAsSummary(OfferEntity offerEntity) {
    return new OfferSummaryDTO(
        offerEntity.getUuid().toString(),
        offerEntity.getModel().getBrand().getName(),
        offerEntity.getModel().getName(),
        offerEntity.getYear(),
        offerEntity.getMileage(),
        offerEntity.getPrice(),
        offerEntity.getEngine(),
        offerEntity.getTransmission(),
        offerEntity.getImageUrl());
  }

  private static OfferEntity map(CreateOfferDTO createOfferDTO) {
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
}
