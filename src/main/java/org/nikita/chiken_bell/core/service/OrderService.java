package org.nikita.chiken_bell.core.service;

import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;
import org.nikita.chiken_bell.core.entity.OrderStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order create(Cart cart, Customer customer, boolean isDelivery);

    Optional<Order> getById(String id);

    List<Order> findByStatus(OrderStatus status);

    Collection<Order> getAll();

    Order updateStatus(String id, OrderStatus status);

    void deleteById(String id);
}
