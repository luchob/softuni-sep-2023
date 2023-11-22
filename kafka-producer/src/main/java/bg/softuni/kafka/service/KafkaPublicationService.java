package bg.softuni.kafka.service;

import static bg.softuni.kafka.config.KafkaConfig.EXCHANGE_RATE_TOPIC;

import bg.softuni.kafka.model.ExchangeRatesDTO;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublicationService {

  private Logger LOGGER = LoggerFactory.getLogger(KafkaPublicationService.class);
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public KafkaPublicationService(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publishExchangeRate(ExchangeRatesDTO exchangeRatesDTO) {
    kafkaTemplate.
        send(EXCHANGE_RATE_TOPIC, UUID.randomUUID().toString(), exchangeRatesDTO).
        whenComplete(
            (res, ex) -> {
              if (ex == null) {
                LOGGER.info(
                    "Kafka message successfully send to topic {}/partition {}/ offset {}. Key = {}.",
                    res.getRecordMetadata().topic(),
                    res.getRecordMetadata().partition(),
                    res.getRecordMetadata().offset(),
                    res.getProducerRecord().key()
                );
              } else {
                LOGGER.error("Problem with the publication to kafka.", ex);
              }
            }

        );
  }
}
