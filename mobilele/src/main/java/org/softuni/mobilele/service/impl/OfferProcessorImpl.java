package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.model.enums.ModelCategoryEnum;
import org.softuni.mobilele.service.BrandService;
import org.softuni.mobilele.service.ModelService;
import org.softuni.mobilele.service.OfferProcessor;
import org.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferProcessorImpl implements OfferProcessor {

    private final BrandService brandService;
    private final ModelService modelService;
    private final OfferService offerService;

    public OfferProcessorImpl(BrandService brandService, ModelService modelService, OfferService offerService) {

        this.brandService = brandService;

        this.modelService = modelService;
        this.offerService = offerService;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {

        final String brandName = createOfferDTO.brand();
        final BrandEntity brandEntity = this.brandService.findByName(brandName)
                .orElseGet(() -> this.brandService.createBrandEntity(brandName));

        final String modelName = createOfferDTO.model();
        ModelEntity modelEntity = this.modelService.findByBrandAndName(brandEntity, modelName)
                .orElseGet(() -> this.modelService.createModelEntity(ModelCategoryEnum.CAR, brandEntity, modelName));

        final OfferEntity newOffer = this.offerService.createOffer(createOfferDTO, modelEntity);

        return newOffer.getUuid();
    }

}
