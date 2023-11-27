package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * DTO for converting currencies
 *
 * @param target the target currency
 * @param amount the amount that has to be converted
 */
public record ConvertRequestDTO(
    @NotEmpty String target,
    @NotNull @Positive BigDecimal amount) {
}
