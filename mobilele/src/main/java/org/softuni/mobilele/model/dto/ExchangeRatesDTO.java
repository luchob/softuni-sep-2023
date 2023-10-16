package org.softuni.mobilele.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record ExchangeRatesDTO(String base, Map<String, BigDecimal> rates) {

}
