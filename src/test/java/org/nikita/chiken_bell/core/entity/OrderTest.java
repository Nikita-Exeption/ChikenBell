package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.exception.CustomerAdressEmptyException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private static Cart cart;

    private static final String NAME = "Nik";
    private static final String PHONE = "123-456-78-90";

    @BeforeAll
    static void init(){
        cart = new Cart();
        Product product = new Product("Apple", BigDecimal.TEN);
        cart.addProduct(product);
    }

    @Test
    void testInitOrderWithDelivery(){
        Customer customer = new Customer(NAME, PHONE, "Adress");
        Order order = new Order(cart, customer, true);

        BigDecimal expected = BigDecimal.TEN;
        assertEquals(expected, order.getSum());
    }

    @Test
    void testInitOrderWithoutDelivery(){
        Customer customer = new Customer(NAME, PHONE, "Adress");
        Order order = new Order(cart, customer, false);

        BigDecimal expected = BigDecimal.TEN;
        assertEquals(1,order.getProducts().size());
        assertEquals(expected, order.getSum());
    }

    @Test
    void testInitOrderWithDeliveryButNotHaveAdress(){
        Customer customer = new Customer(NAME, PHONE);
        assertThrows(CustomerAdressEmptyException.class, ()-> new Order(cart, customer, true));
    }

    @Test
    void testGeneratedAnotherId(){
        Customer customer = new Customer(NAME, PHONE);
        Customer customer1 = new Customer("Mike", "012-345-67-89");
        assertNotEquals(customer1.getId(), customer.getId());
    }

}