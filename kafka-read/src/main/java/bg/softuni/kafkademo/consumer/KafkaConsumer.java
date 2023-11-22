package bg.softuni.kafkademo.consumer;

import bg.softuni.kafkademo.model.ExchangeRatesDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  @KafkaListener(
      containerFactory = "kafkaListenerContainerFactory",
      topics = "exchange_rate",
      groupId = "softuni"
  )
  public void readMessage(
      @Header(KafkaHeaders.RECEIVED_KEY) String key,
      ExchangeRatesDTO exRate) {
    System.out.println("EX RATE: " + exRate);
    System.out.println("Key: " + key);
  }

}
