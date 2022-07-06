package org.nikita.chiken_bell.core.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Cart;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.entity.Order;
import org.nikita.chiken_bell.core.exception.OrderNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private final OrderServiceImpl service = new OrderServiceImpl();
    private Order order;

    private static final Cart CART = new Cart();
    private static final Customer CUSTOMER = new Customer("NIk", "099-099-09-90", "Street");
    private static final boolean IS_DELIVERY = false;

    @BeforeEach
    void before(){
        order = service.create(CART, CUSTOMER, IS_DELIVERY);
    }

    @Test
    void testCreate() {
        assertEquals(1, service.getAll().size());
        Order order = service.create(CART, CUSTOMER, IS_DELIVERY);

        assertEquals(CUSTOMER.getAddress(), order.getDeliveryAddress());
        assertEquals(IS_DELIVERY, order.isDelivery());
        assertEquals(2, service.getAll().size());
    }

    @Test
    void testGetByIdPositiveCase() {
        assertNotNull(service.getById(order.getId()));

        Order getOrder = service.getById(order.getId()).orElseThrow(OrderNotFoundException::new);

        assertEquals(order.getId(), getOrder.getId());
        assertEquals(order.getSum(), getOrder.getSum());
        assertEquals(order.getDeliveryAddress(), getOrder.getDeliveryAddress());
        assertEquals(order.getProducts(), getOrder.getProducts());
    }

    @Test
    void testGetByIdNegativeCase(){
        assertThrows(OrderNotFoundException.class,() -> service.getById("Nothing").orElseThrow(OrderNotFoundException::new));
    }

    @Test
    void getAll() {
         assertEquals(1, service.getAll().size());

         service.create(new Cart(), new Customer("Nothing", "099-321-90-67"), false);

         assertEquals(2, service.getAll().size());
    }

    @Test
    void delete() {
        assertEquals(1, service.getAll().size());

        service.delete(order.getId());

        assertEquals(0, service.getAll().size());
    }
}