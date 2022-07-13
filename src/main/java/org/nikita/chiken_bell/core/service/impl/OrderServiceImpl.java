package org.nikita.chiken_bell.core.service.impl;

import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;
import org.nikita.chiken_bell.core.entity.OrderStatus;
import org.nikita.chiken_bell.core.exception.InvalidOrderStatusException;
import org.nikita.chiken_bell.core.exception.OrderNotFoundException;
import org.nikita.chiken_bell.core.service.OrderService;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<Order> findByStatus(OrderStatus status) {
        return orders.stream().filter(x -> x.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    public Collection<Order> getAll() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public Order updateStatus(String id, OrderStatus status) {
        Order order = getById(id).orElseThrow(OrderNotFoundException::new);
        if (order.getStatus().getCapacity() > status.getCapacity()){
            throw new InvalidOrderStatusException();
        }
        order.setOrderStatus(status);
        return order;
    }

    @Override
    public void deleteById(String id) {
        orders.remove(getById(id).orElseThrow(OrderNotFoundException::new));
    }
}

