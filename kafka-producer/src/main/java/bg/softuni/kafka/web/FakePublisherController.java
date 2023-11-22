package bg.softuni.kafka.web;

import bg.softuni.kafka.model.ExchangeRatesDTO;
import bg.softuni.kafka.service.KafkaPublicationService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakePublisherController {

  private final KafkaPublicationService kafkaPublicationService;

  public FakePublisherController(KafkaPublicationService kafkaPublicationService) {
    this.kafkaPublicationService = kafkaPublicationService;
  }

  @GetMapping("/publish")
  public String publish() {
    var toPublish = new ExchangeRatesDTO(
        "USD",
        System.currentTimeMillis(),
        Map.of("BGN", BigDecimal.valueOf(1.840515),
            "EUR", BigDecimal.valueOf(0.937668)
        )
    );

    kafkaPublicationService.publishExchangeRate(toPublish);

    return "OK";
  }

}
