package org.softuni.mobilele.service;

import org.softuni.mobilele.model.entity.BrandEntity;

import java.util.Optional;

public interface BrandService {

    Optional<BrandEntity> findByName(String brandName);

    BrandEntity createBrandEntity(String brandName);
}
