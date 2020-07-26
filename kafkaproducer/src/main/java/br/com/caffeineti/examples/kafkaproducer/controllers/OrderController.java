package br.com.caffeineti.examples.kafkaproducer.controllers;

import br.com.caffeineti.examples.kafkaproducer.model.Order;
import br.com.caffeineti.examples.kafkaproducer.producers.OrderProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody Order order) throws JsonProcessingException {
        orderProducer.send(order);
    }
}