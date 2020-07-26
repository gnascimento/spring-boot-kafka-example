package br.com.caffeineti.examples.kafkaproducer.producers;

import br.com.caffeineti.examples.kafkaproducer.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class OrderProducer {

    @Value("${order.topic}")
    private String orderTopic;

    @Value("${order.message.root.key}")
    private String orderMessageRootKey;

    @Autowired
    private final KafkaTemplate kafkaTemplate;

    public OrderProducer(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Order order) throws JsonProcessingException {
        final String messageKey = String.join(".", orderMessageRootKey, UUID.randomUUID().toString());
        var objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);
        kafkaTemplate.send(orderTopic, messageKey, orderJson);
    }
}