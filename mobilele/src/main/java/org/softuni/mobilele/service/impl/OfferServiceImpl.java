package org.softuni.mobilele.service.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.dto.OfferDetailDTO;
import org.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.softuni.mobilele.model.entity.BaseEntity;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;
import org.softuni.mobilele.model.enums.UserRoleEnum;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.MonitoringService;
import org.softuni.mobilele.service.OfferService;
import org.softuni.mobilele.service.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final ModelRepository modelRepository;
  private final MonitoringService monitoringService;
  private final UserRepository userRepository;

  public OfferServiceImpl(
      OfferRepository offerRepository,
      ModelRepository modelRepository,
      MonitoringService monitoringService,
      UserRepository userRepository) {
    this.offerRepository = offerRepository;
    this.modelRepository = modelRepository;
    this.monitoringService = monitoringService;
    this.userRepository = userRepository;
  }

  @Override
  public UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails seller) {

    OfferEntity newOffer = map(createOfferDTO);
    ModelEntity modelEntity = modelRepository.findById(createOfferDTO.modelId()).orElseThrow(() ->
        new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found!"));
    UserEntity sellerEntity = userRepository.findByEmail(seller.getUsername()).orElseThrow(() ->
        new IllegalArgumentException("User with email " + seller.getUsername() + " not found!"));


    newOffer.setModel(modelEntity);
    newOffer.setSeller(sellerEntity);

    newOffer = offerRepository.save(newOffer);

    return newOffer.getUuid();
  }

  @Override
  public Page<OfferSummaryDTO> getAllOffers(Pageable pageable) {

    monitoringService.logOfferSearch();

    return offerRepository
        .findAll(pageable)
        .map(OfferServiceImpl::mapAsSummary);
  }

  @Override
  public Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID, UserDetails viewer) {
    return offerRepository
        .findByUuid(offerUUID)
        .map(o -> this.mapAsDetails(o, viewer));
  }

  @Override
  @Transactional
  public void deleteOffer(UUID offerUUID) {
    offerRepository.deleteByUuid(offerUUID);
  }

  private OfferDetailDTO mapAsDetails(OfferEntity offerEntity, UserDetails viewer) {

    return new OfferDetailDTO(
        offerEntity.getUuid().toString(),
        offerEntity.getModel().getBrand().getName(),
        offerEntity.getModel().getName(),
        offerEntity.getYear(),
        offerEntity.getMileage(),
        offerEntity.getPrice(),
        offerEntity.getEngine(),
        offerEntity.getTransmission(),
        offerEntity.getImageUrl(),
        offerEntity.getSeller().getFirstName(),
        isOwner(offerEntity, viewer != null ? viewer.getUsername() : null));
  }

  @Override
  public boolean isOwner(UUID uuid, String userName) {
    return isOwner(
        offerRepository.findByUuid(uuid).orElse(null),
        userName
    );
  }

  private boolean isOwner(OfferEntity offerEntity, String userName) {
    if (offerEntity == null || userName == null) {
      // anonymous users own no offers
      // missing offers are meaningless
      return false;
    }

    UserEntity viewerEntity =
        userRepository
            .findByEmail(userName)
            .orElseThrow(() -> new IllegalArgumentException("Unknown user..."));

    if (isAdmin(viewerEntity)) {
      // all admins own all offers
      return true;
    }

    return Objects.equals(
        offerEntity.getSeller().getId(),
        viewerEntity.getId());
  }

  private boolean isAdmin(UserEntity userEntity) {
    return userEntity
        .getRoles()
        .stream()
        .map(UserRoleEntity::getRole)
        .anyMatch(r -> UserRoleEnum.ADMIN == r);
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
