package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * Money representation.
 *
 * @param currency the currency
 * @param amount the amount of that currency
 */
public record MoneyDTO(@NotEmpty String currency, BigDecimal amount) {
}
