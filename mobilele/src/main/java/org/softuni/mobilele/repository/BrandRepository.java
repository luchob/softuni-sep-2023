package org.softuni.mobilele.repository;

import java.util.List;
import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

//  @Query("SELECT b FROM BrandEntity b "
//      + "JOIN FETCH b.models ")
  @EntityGraph(
      value = "brandWithModels",
      attributePaths = "models"
  )
  @Query("SELECT b FROM BrandEntity b")
  List<BrandEntity> getAllBrands();

}