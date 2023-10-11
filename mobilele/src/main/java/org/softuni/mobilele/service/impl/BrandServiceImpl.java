package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.repository.BrandRepository;
import org.softuni.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
  private final BrandRepository brandRepository;

  public BrandServiceImpl(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

}
