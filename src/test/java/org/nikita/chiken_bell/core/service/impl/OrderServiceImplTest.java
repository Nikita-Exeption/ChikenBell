package org.nikita.chiken_bell.core.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;
import org.nikita.chiken_bell.core.entity.OrderStatus;
import org.nikita.chiken_bell.core.exception.InvalidOrderStatusException;

import java.util.List;
import java.util.Optional;

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
    void testCreateAndCheckThatAllNewOrderHaveStatusNEW(){
        assertEquals(order.getStatus(), OrderStatus.NEW);

        Order newOrder = service.create(CART, CUSTOMER, false);

        assertEquals(List.of(OrderStatus.NEW, OrderStatus.NEW), List.of(order.getStatus(), newOrder.getStatus()));
    }

    @Test
    void getByIdPositiveCase() {
        assertNotNull(service.getById(order.getId()));

        Optional<Order> getOptionalOrder = service.getById(order.getId());

        assertTrue(getOptionalOrder.isPresent());

        Order getOrder = getOptionalOrder.get();

        assertEquals(getOrder.getId(), order.getId());
        assertEquals(getOrder.getProducts(), order.getProducts());
        assertEquals(getOrder.getDeliveryAddress(), order.getDeliveryAddress());
        assertEquals(getOrder.getSum(), order.getSum());
    }

    @Test
    void testFindByStatus(){
        assertEquals(Optional.of(List.of(order)), service.findByStatus(order.getStatus()));

        Order newOrder = service.create(CART, CUSTOMER, true);

        assertEquals(Optional.of(List.of(order, newOrder)), service.findByStatus(order.getStatus()));
    }

    @Test
    void testGetAll() {
        assertEquals(List.of(order), service.getAll());

        Order newOrder = service.create(CART, CUSTOMER, false);

        assertEquals(List.of(order, newOrder), service.getAll());
    }

    @Test
    void testUpdateStatus(){
        assertEquals(order.getStatus(), OrderStatus.NEW);

        service.updateStatus(order.getId(), OrderStatus.READY);

        assertEquals(order.getStatus(), OrderStatus.READY);
    }

    @Test
    void testUpdateStatusNegativeCase(){
        assertEquals(order.getStatus(), OrderStatus.NEW);

        service.updateStatus(order.getId(), OrderStatus.DONE);

        assertEquals(order.getStatus(), OrderStatus.DONE);

        assertThrows(InvalidOrderStatusException.class, () -> service.updateStatus(order.getId(), OrderStatus.READY));
    }

    @Test
    void deleteById() {
        assertEquals(1, service.getAll().size());

        service.deleteById(order.getId());

        assertEquals(0, service.getAll().size());
    }
}