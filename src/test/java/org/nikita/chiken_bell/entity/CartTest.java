package org.nikita.chiken_bell.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private static Cart cart;
    private static Product product;

    @BeforeAll
    static void init(){
        cart = new Cart();
        product = new Product("Apple", BigDecimal.TEN);
    }

    @Test
    void addProduct() {
        cart.addProduct(product);

        BigDecimal expected = BigDecimal.TEN;
        assertNotNull(cart.getProducts());
        assertEquals(expected, cart.getSum());
    }

    @Test
    void removeProduct() {
        cart.addProduct(product);
        assertEquals(1, cart.getProducts().size());

        cart.removeProduct(product);

        int expected = 0;
        assertEquals(expected, cart.getProducts().size());
    }
}