package org.nikita.chiken_bell.core.service.impl;

import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;
import org.nikita.chiken_bell.core.exception.OrderNotFoundException;
import org.nikita.chiken_bell.core.service.OrderService;

import java.util.*;

public class OrderServiceImpl implements OrderService {

    private final List<Order> orders =new ArrayList<>();

    @Override
    public Order create(Cart cart, Customer customer, boolean isDelivery) {
        Order order = new Order(cart, customer, isDelivery);
        orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> getById(String id) {
        return orders.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Order> getAll() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public void deleteById(String id) {
        orders.remove(getById(id).orElseThrow(OrderNotFoundException::new));
    }
}

