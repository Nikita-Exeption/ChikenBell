package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartTest {

    private Cart cart;
    private Product product;

    @BeforeEach
    void init(){
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