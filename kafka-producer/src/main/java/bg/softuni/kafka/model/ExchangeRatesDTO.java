package bg.softuni.kafka.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

//{
//    "base": "USD",
//    "rates": {
//        "BGN": 1.840515,
//        "EUR": 0.937668
//      }
//    }
public record ExchangeRatesDTO(String base,
                               long timestamp,
                               Map<String, BigDecimal> rates) {
}
