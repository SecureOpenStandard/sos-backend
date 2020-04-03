package secure.open.standard.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cloudevents.CloudEvent;
import io.cloudevents.kafka.CloudEventsKafkaConsumer;
import io.cloudevents.kafka.CloudEventsKafkaProducer;
import io.cloudevents.v03.AttributesImpl;
import io.cloudevents.v03.CloudEventBuilder;
import io.cloudevents.v03.CloudEventImpl;
import io.cloudevents.v03.kafka.Marshallers;
import io.cloudevents.v03.kafka.Unmarshallers;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Service;
import secure.open.standard.backend.events.FoobarEvent;

import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
@SpringBootApplication
@EnableKafka
public class EventService {

    @Value("${spring.kafka.servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.groupId}")
    private String groupId;

    private static final Logger LOG = LoggerFactory.getLogger(EventService.class);


    @EventListener
    public void onApplicationEvent(final ApplicationStartedEvent event) throws JsonProcessingException {




        Properties props = new Properties();

        /* all other properties */

        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (CloudEventsKafkaProducer<String, AttributesImpl, FoobarEvent>
                     ceProducer = new CloudEventsKafkaProducer<>(props,
                Marshallers.structured())) {

            // Build an event
            CloudEventImpl<FoobarEvent> ce =
                    CloudEventBuilder.<FoobarEvent>builder()
                            .withDatacontenttype("application/json")
                            .withData(new FoobarEvent("fooobar", "x10", "/source", "user1"))
                            .withId("x10")
                            .withSource(URI.create("/source"))
                            .withType("foobar-event")
                            .build();

            // Produce the event
            ceProducer.send(new ProducerRecord<>("test", ce));
        }




        Properties consumerProps = new Properties();

        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        try (CloudEventsKafkaConsumer<String, AttributesImpl, FoobarEvent> ceConsumer =
                     new CloudEventsKafkaConsumer<>(consumerProps, Unmarshallers.structured(FoobarEvent.class))) {

            // Subscribe . . .
            ceConsumer.subscribe(Collections.singletonList("test"));

            // Pool . . .
            ConsumerRecords<String, CloudEvent<AttributesImpl, FoobarEvent>> records = ceConsumer.poll(Duration.ofMillis(10000));

            // Use the records
            records.forEach(cloudevent -> {


                LOG.info(cloudevent.toString());

                LOG.info(cloudevent.value().getData().toString());
            });
        }
    }
}
