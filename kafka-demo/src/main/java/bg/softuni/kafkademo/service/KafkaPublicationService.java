package bg.softuni.kafkademo.service;

import bg.softuni.kafkademo.model.ExchangeRatesDTO;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublicationService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(KafkaPublicationService.class);

  private final KafkaTemplate<String, Object> kafkaTemplate;

  private static final String EXCHANGE_RATE_TOPIC = "exchange_rate";

  public KafkaPublicationService(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publishExchangeRate(ExchangeRatesDTO exchangeRatesDTO) {
    kafkaTemplate
        .send(EXCHANGE_RATE_TOPIC, UUID.randomUUID().toString(), exchangeRatesDTO)
        .whenComplete(
            (res, ex) -> {
              if (ex == null) {
                LOGGER.info("Successfully sent to topic/key/partition {}/{}/{}",
                    res.getRecordMetadata().topic(),
                    res.getProducerRecord().key(),
                    res.getRecordMetadata().partition());
              } else {
                LOGGER.error("Problem publishing the message.", ex);
              }
            }
        );
  }
}
