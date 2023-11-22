package bg.softuni.kafka.config;

import bg.softuni.kafka.model.ExchangeRatesDTO;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConfig {

  @Bean
  public ConsumerFactory<String, ExchangeRatesDTO> consumerFactory(KafkaProperties kafkaProperties) {
    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
  }


  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, ExchangeRatesDTO> kafkaContainerFactory(
      ConsumerFactory<String, ExchangeRatesDTO> consumerFactory
  ) {
    ConcurrentKafkaListenerContainerFactory<String, ExchangeRatesDTO> containerFactory =
        new ConcurrentKafkaListenerContainerFactory<>();
    containerFactory.setConsumerFactory(consumerFactory);
    return containerFactory;
  }
}
