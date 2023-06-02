package ru.job4j.job4j_kitchen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.job4j_kitchen.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
