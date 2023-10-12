package org.softuni.mobilele.repository;

import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    @EntityGraph(attributePaths = {"brand"})
    @Query("SELECT m FROM ModelEntity m") // or without @EntityGraph: @Query("SELECT m FROM ModelEntity m JOIN FETCH m.brand")
    List<ModelEntity> getAllInclBrand ();

    Optional<ModelEntity> findByBrandAndName (BrandEntity brand, String name);

}
