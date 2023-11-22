package bg.softuni.kafkademo.web;

import bg.softuni.kafkademo.model.ExchangeRatesDTO;
import bg.softuni.kafkademo.service.KafkaPublicationService;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicationController {

  private final KafkaPublicationService publicationService;

  public PublicationController(KafkaPublicationService publicationService) {
    this.publicationService = publicationService;
  }

  @GetMapping("/publish")
  public ExchangeRatesDTO publish() {
    ExchangeRatesDTO exRate = new ExchangeRatesDTO("USD",
        Map.of(
            "EUR", BigDecimal.ONE,
            "BGN", BigDecimal.ONE
        )
    );

    publicationService.publishExchangeRate(exRate);

    return exRate;
  }

}
