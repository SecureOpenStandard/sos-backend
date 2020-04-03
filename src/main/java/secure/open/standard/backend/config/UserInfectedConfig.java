package secure.open.standard.backend.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import secure.open.standard.backend.events.UserInfected;

import java.util.Map;

@Configuration
@EnableKafka
public class UserInfectedConfig extends KafkaConfigs {

  @Bean(name = "kafka.producer.UserInfected")
  public ProducerFactory<String, UserInfected> userInfectedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(senderConfigs());
  }

  @Bean(name = "kafka.template.UserInfected")
  public KafkaTemplate<String, UserInfected> userInfectedKafkaTemplate() {
    return new KafkaTemplate<>(userInfectedProducerFactory());
  }

  @Bean(name = "kafka.consumer.UserInfected")
  public ConsumerFactory<String, UserInfected> userInfectedFactory() {
    return new DefaultKafkaConsumerFactory<>(
        consumerConfig(),
        new StringDeserializer(),
        new JsonDeserializer<>(UserInfected.class));
  }

  @Bean(name = "kafka.listener.UserInfected")
  public ConcurrentKafkaListenerContainerFactory<String, UserInfected> listenerContainerFactory() {

    final ConcurrentKafkaListenerContainerFactory<String, UserInfected> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(userInfectedFactory());
    return factory;
  }
}
