package org.softuni.mobilele.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;

public record CreateOfferDTO(
        String description,
        Long modelId,
        EngineEnum engine,
        TransmissionEnum transmission,
        String imageUrl,
        Integer mileage,
        Integer price,
        Integer year
) {

}
