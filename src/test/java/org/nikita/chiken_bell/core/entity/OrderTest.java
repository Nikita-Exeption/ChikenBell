package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.exception.CustomerAdressEmptyException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    private static Cart cart;



    @BeforeAll
    static void init(){
        cart = new Cart();
        Product product = new Product("Apple", BigDecimal.TEN);
        cart.addProduct(product);
    }

    @Test
    void testInitOrderWithDelivery(){
        Customer customer = new Customer("Nik", "123-456-78-90", "Adress");
        Order order = new Order(cart, customer, true);

        BigDecimal expected = BigDecimal.TEN;
        assertEquals(expected, order.getSum());
    }

    @Test
    void testInitOrderWithoutDelivery(){
        Customer customer = new Customer("Nik", "123-456-78-90", "Adress");
        Order order = new Order(cart, customer, false);

        BigDecimal expected = BigDecimal.TEN;
        assertEquals(expected, order.getSum());
    }

    @Test
    void testInitOrderWithDeliveryButNotHaveAdress(){
        Customer customer = new Customer("Nik", "123-456-78-90");
        assertThrows(CustomerAdressEmptyException.class, ()-> new Order(cart, customer, true));
    }

}