package bg.softuni.kafkademo.config;

import bg.softuni.kafkademo.model.ExchangeRatesDTO;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConfig {

  private final KafkaProperties kafkaProperties;

  public KafkaConfig(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  @Bean
  public ConsumerFactory<String, ExchangeRatesDTO> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, ExchangeRatesDTO> kafkaListenerContainerFactory(
      ConsumerFactory<String, ExchangeRatesDTO> consumerFactory
  ) {

    ConcurrentKafkaListenerContainerFactory<String, ExchangeRatesDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);

    return factory;
  }
}
