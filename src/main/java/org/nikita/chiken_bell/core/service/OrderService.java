package org.nikita.chiken_bell.core.service;

import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order create(Cart cart, Customer customer, boolean isDelivery);

    Optional<Order> getById(String id);

    Optional<List<Order>> findByStatus(Order.OrderStatus status);

    Collection<Order> getAll();

    Order updateStatus(String id, Order.OrderStatus status);

    void deleteById(String id);
}
