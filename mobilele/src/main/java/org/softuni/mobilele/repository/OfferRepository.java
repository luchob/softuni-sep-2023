package org.softuni.mobilele.repository;

import java.util.Optional;
import java.util.UUID;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long>,
    JpaSpecificationExecutor<OfferEntity> {
  Optional<OfferEntity> findByUuid(UUID uuid);

  void deleteByUuid(UUID uuid);
}
