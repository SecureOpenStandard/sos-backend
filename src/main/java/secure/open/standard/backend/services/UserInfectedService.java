package secure.open.standard.backend.services;

import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import secure.open.standard.backend.events.UserInfected;
import secure.open.standard.backend.security.JweService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class UserInfectedService {

    @Autowired
    private JweService jweService;
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
        LOG.info("received: " + userInfected.getPayload().toString());


            for (String payload : userInfected.getPayload()) {
                try {
                    String plain = jweService.decrypt(payload);
                    LOG.info("was decrypted as '" + plain+"'");
                    this.publishedKeys.addAll(userInfected.getPayload());
                } catch (Exception e) {
                    LOG.info("could not be decrypted, ignoring");
                }
            }
    }

    public List<String> getPublishedKeys() {
        return publishedKeys;
    }
}
