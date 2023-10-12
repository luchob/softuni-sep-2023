package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.repository.BrandRepository;
import org.softuni.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
  private final BrandRepository brandRepository;

  public BrandServiceImpl(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

    @Override
    public Optional<BrandEntity> findByName(String brandName) {
        return this.brandRepository.findByName(brandName);
    }

    @Override
    public BrandEntity createBrandEntity(String brandName) {
        final BrandEntity newBrandEntity = new BrandEntity().setName(brandName);

        return brandRepository.saveAndFlush(newBrandEntity);
    }
}
