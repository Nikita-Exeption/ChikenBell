package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.exception.CustomerAdressEmptyException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private static Cart cart;

    private static final String CUSTOMER_NAME = "Nik";
    private static final String CUSTOMER_PHONE = "123-456-78-90";
    private static final String CUSTOMER_ADDRESS = "Address";

    @BeforeAll
    static void init(){
        cart = new Cart();
        Product product = new Product("Apple", BigDecimal.TEN);
        cart.addProduct(product);
    }

    @Test
    void testInitOrderWithDelivery(){
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_PHONE, CUSTOMER_ADDRESS);
        Order order = new Order(cart, customer, true);

        assertNotNull(order.getId());
        assertEquals(cart.getSum(), order.getSum());
        assertEquals(CUSTOMER_ADDRESS, order.getDeliveryAddress());
        assertTrue(order.isDelivery());
        //check product list
        assertTrue(cart.getProducts().containsAll(order.getProducts()));
    }

    @Test
    void testInitOrderWithoutDelivery(){
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_PHONE, CUSTOMER_ADDRESS);
        Order order = new Order(cart, customer, false);

        BigDecimal expected = BigDecimal.TEN;
        // check product list
        assertTrue(cart.getProducts().containsAll(order.getProducts()));

        assertEquals(expected, order.getSum());
    }

    @Test
    void testInitOrderWithDeliveryButNotHaveAddress(){
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_PHONE);
        assertThrows(CustomerAdressEmptyException.class, ()-> new Order(cart, customer, true));
    }
}
