package ru.job4j.job4j_kitchen.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_kitchen.model.Order;
import ru.job4j.job4j_kitchen.model.Status;
import ru.job4j.job4j_kitchen.repository.OrderRepository;

@Service
public class OrderService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final OrderRepository orderRepository;

    public OrderService(KafkaTemplate<String, Object> kafkaTemplate, OrderRepository orderRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "preOrder")
    public void receiveOrder(Order order) {
        orderRepository.save(order);
        order.setStatus(Status.completed);
        kafkaTemplate.send("statusDish", order.getStatus());
    }
}
