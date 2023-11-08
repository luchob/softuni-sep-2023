package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ConvertRequestDTO(@NotEmpty String target, @NotNull @Positive BigDecimal amount) {
}
