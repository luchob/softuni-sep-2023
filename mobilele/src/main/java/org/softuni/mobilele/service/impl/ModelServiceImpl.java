package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.BrandDTO;
import org.softuni.mobilele.model.dto.ModelDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl (ModelRepository modelRepository) {

        this.modelRepository = modelRepository;
    }

    @Override
    public List<BrandDTO> getAllGroupedByBrand () {

        return modelRepository.getAllInclBrand().stream()
                .sorted(Comparator.comparing(ModelEntity::getName))
                .collect(Collectors.groupingBy(ModelEntity::getBrand))
                .entrySet().stream()
                .map(entry -> new BrandDTO(entry.getKey().getName(),
                                           entry.getValue().stream()
                                                   .map(entity -> new ModelDTO(entity.getId(),
                                                                               entity.getName()))
                                                   .toList()))
                .sorted(Comparator.comparing(BrandDTO::name))
                .toList();
    }


}
