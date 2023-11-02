package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ConvertRequestDTO(@NotEmpty String target, @NotNull @NotEmpty BigDecimal amount) {

}
