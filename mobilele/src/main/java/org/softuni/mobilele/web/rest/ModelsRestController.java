package org.softuni.mobilele.web.rest;

import org.softuni.mobilele.model.dto.BrandDTO;
import org.softuni.mobilele.service.ModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
public class ModelsRestController {

    private final ModelService modelService;

    public ModelsRestController (ModelService modelService) {

        this.modelService = modelService;
    }

    @GetMapping("/all")
    public List<BrandDTO> getAllModels() {

        return this.modelService.getAllGroupedByBrand();
    }

}
