package org.nikita.chiken_bell.core.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;
import org.nikita.chiken_bell.core.exception.OrderNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private final OrderServiceImpl service = new OrderServiceImpl();
    private Order order;

    private static final Cart CART = new Cart();
    private static final Customer CUSTOMER = new Customer("Nik", "099-654-52-54", "address");

    @BeforeEach
    void before(){
        order = service.create(CART, CUSTOMER, true);
    }

    @Test
    void testCreate() {
        assertEquals(1, service.getAll().size());

        Order newOrder = service.create(CART, CUSTOMER, false);

        assertEquals(2, service.getAll().size());
        assertEquals(newOrder.getProducts(), CART.getProducts());
    }

    @Test
    void getByIdPositiveCase() {
        assertNotNull(service.getById(order.getId()));

        Order getOrder = service.getById(order.getId()).orElseThrow(OrderNotFoundException::new);

        assertEquals(getOrder.getId(), order.getId());
        assertEquals(getOrder.getProducts(), order.getProducts());
        assertEquals(getOrder.getDeliveryAddress(), order.getDeliveryAddress());
        assertEquals(getOrder.getSum(), order.getSum());
    }

    @Test
    void getAll() {
        assertEquals(List.of(order), service.getAll());

        Order newOrder = service.create(CART, CUSTOMER, false);

        assertEquals(List.of(order, newOrder), service.getAll());
    }

    @Test
    void deleteById() {
        assertEquals(1, service.getAll().size());

        service.deleteById(order.getId());

        assertEquals(0, service.getAll().size());
    }
}