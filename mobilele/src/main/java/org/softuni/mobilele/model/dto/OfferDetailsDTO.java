package org.softuni.mobilele.model.dto;

import java.math.BigDecimal;
import java.util.UUID;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;

public record OfferDetailsDTO(String description,
                              UUID offerId,
                              EngineEnum engine,
                              String imageUrl,
                              Integer mileage,
                              BigDecimal price,
                              TransmissionEnum transmission,
                              Integer year,
                              String brand,
                              String model){}
