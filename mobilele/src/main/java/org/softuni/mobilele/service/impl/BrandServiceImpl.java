package org.softuni.mobilele.service.impl;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.softuni.mobilele.model.dto.BrandDTO;
import org.softuni.mobilele.model.dto.ModelDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.repository.BrandRepository;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

  public static final Comparator<ModelDTO> MODEL_DTO_BY_NAME_ASC = Comparator.comparing(ModelDTO::name);
  public static final Comparator<String> BRAND_NAME_ASC = String::compareTo;
  private final ModelRepository modelRepository;
  private final BrandRepository brandRepository;

  public BrandServiceImpl(ModelRepository modelRepository,
                          BrandRepository brandRepository) {
    this.modelRepository = modelRepository;
    this.brandRepository = brandRepository;
  }

  @Override
  public List<BrandDTO> getAllBrands() {

    return getAllBrandsSorted(BRAND_NAME_ASC, MODEL_DTO_BY_NAME_ASC);
  }

  public List<BrandDTO> getAllBrandsSorted(Comparator<String> brandNameComparator,
                                           Comparator<ModelDTO> modelDTOComparator) {

    final Supplier<TreeMap<String, List<ModelDTO>>> treeMapSupplier = () ->
            new TreeMap<>(brandNameComparator);

    final Function<ModelEntity, ModelDTO> mapModelEntityToDto = (model) ->
            new ModelDTO(model.getId(), model.getName());

    final Function<List<ModelDTO>, List<ModelDTO>> getSortedList = (list) ->
            list.stream().sorted(modelDTOComparator).toList();

    return modelRepository.findAll().stream()
            .collect(Collectors.groupingBy(
                    model -> model.getBrand().getBrand(),
                    treeMapSupplier,
                    Collectors.mapping(
                            mapModelEntityToDto,
                            Collectors.collectingAndThen(
                                    Collectors.toList(),
                                    getSortedList)
                    )))
            .entrySet().stream()
            .map(entry -> new BrandDTO(entry.getKey(),
                                       entry.getValue()))
            .toList();
  }

}
