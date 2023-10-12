package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.BrandDTO;
import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.enums.ModelCategoryEnum;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    List<BrandDTO> getAllGroupedByBrand ();

    Optional<ModelEntity> findByBrandAndName(BrandEntity brandEntity, String modelName);

    ModelEntity createModelEntity(ModelCategoryEnum car, BrandEntity brandEntity, String modelName);
}
