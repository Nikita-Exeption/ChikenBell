package org.nikita.chiken_bell.core.service;

import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {

    Order create(Cart cart, Customer customer, boolean isDelivery);

    Optional<Order> getById(String id);

    Collection<Order> getAll();

    void delete(String id);


}
