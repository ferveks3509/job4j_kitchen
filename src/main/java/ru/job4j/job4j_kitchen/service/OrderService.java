package ru.job4j.job4j_kitchen.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_kitchen.model.Order;
import ru.job4j.job4j_kitchen.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "job4j_orders")
    public void receiveOrder(Order order) {
        orderRepository.save(order);
    }
}
