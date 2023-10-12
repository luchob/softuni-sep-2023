package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.*;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;
import org.softuni.mobilele.model.validation.YearNotInTheFuture;

public record CreateOfferDTO(

        @NotEmpty(message = "{description.NotEmpty}")
        @Size(min = 5, max = 512, message = "{description.Size}")
        String description,

        @NotEmpty(message = "{brand.NotEmpty}")
        String brand,

        @NotEmpty(message = "{model.NotEmpty}")
        String model,

        @NotNull(message = "{engine.NotNull}")
        EngineEnum engine,

        @NotNull(message = "{transmission.NotNull}")
        TransmissionEnum transmission,

        @NotEmpty(message = "{imageUrl.NotEmpty}")
        String imageUrl,

        @NotNull(message = "{mileage.NotNull}")
        @Positive(message = "{mileage.Positive}")
        Integer mileage,

        @NotNull(message = "{price.NotNull}")
        @Positive(message = "{price.Positive}")
        Integer price,

        @NotNull(message = "{year.NotNull}")
        @Min(value = 1930, message = "{year.Min}")
        @YearNotInTheFuture
        Integer year
) {
    public static CreateOfferDTO empty () {

        return new CreateOfferDTO(null, null,null, null, null, null, null, null, null);
    }
}
