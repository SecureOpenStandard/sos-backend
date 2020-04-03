package secure.open.standard.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import secure.open.standard.backend.consumers.UserUpdatedConsumer;
import secure.open.standard.backend.events.UserInfected;

import java.util.ArrayList;
import java.util.List;

@Service()
public class UserInfectedService {

    private ArrayList<String> publishedKeys = new ArrayList<String>();

    private final String topic = "user-infected";

    private static final Logger LOG = LoggerFactory.getLogger(UserInfectedService.class);

    private final KafkaTemplate<String, UserInfected> userInfectedProducer;

    public UserInfectedService(final KafkaTemplate<String, UserInfected> userInfectedProducer) {
        this.userInfectedProducer = userInfectedProducer;
    }

    public void send(final UserInfected event) {

        this.userInfectedProducer.send(topic, event);
    }


    @KafkaListener(topics = topic, containerFactory = "kafka.listener.UserInfected")
    public void receive(final UserInfected userInfected) {
        LOG.info(userInfected.getPayload().toString());

        this.publishedKeys.addAll(userInfected.getPayload());
    }

    public List<String> getPublishedKeys() {
        return publishedKeys;
    }
}
