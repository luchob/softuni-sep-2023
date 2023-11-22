package bg.softuni.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

  public static String EXCHANGE_RATE_TOPIC = "exchange_rates";

  @Bean
  public NewTopic exRateTopic() {
    return TopicBuilder.name("exchange_rates")
        .partitions(2)
        .build();
  }

  @Bean
  public ProducerFactory<String, Object> producerFactory(KafkaProperties kafkaProperties) {
    return new DefaultKafkaProducerFactory<>(
        kafkaProperties.buildProducerProperties()
    );
  }

  @Bean
  public KafkaTemplate<String, Object> jsonKafkaTemplate(
      ProducerFactory<String, Object> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

}
