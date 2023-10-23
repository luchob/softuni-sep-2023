package org.softuni.mobilele.model.dto;

import java.math.BigDecimal;
import java.util.Map;

//{
//    "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
//    "license": "https://openexchangerates.org/license",
//    "timestamp": 1698080400,
//    "base": "USD",
//    "rates": {
//    "BGN": 1.840515,
//    "EUR": 0.937668
//    }
//    }
public record ExchangeRatesDTO(String base, Map<String, BigDecimal> rates) {
}
