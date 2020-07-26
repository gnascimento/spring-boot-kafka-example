package br.com.caffeineti.examples.kafkaconsumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = { "${order.topic}" }, groupId = "${consumer.group}")
    public void consume(final ConsumerRecord consumerRecord) {
        log.info("key: " + consumerRecord.key());
        log.info("Headers: " + consumerRecord.headers());
        log.info("Partion: " + consumerRecord.partition());
        log.info("Order: " + consumerRecord.value());
    }

}
