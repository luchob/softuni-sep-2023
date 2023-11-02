package org.softuni.mobilele.model.dto;

import java.math.BigDecimal;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;

public record OfferSummaryDTO(
    String id,
    String brand,
    String model,
    int year,
    int mileage,
    BigDecimal price,
    EngineEnum engine,
    TransmissionEnum transmission,
    String imageUrl
) {
  public String summary() {
    return brand + " " + model + ", " + year;
  }
}
