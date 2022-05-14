package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.exception.ProductNotFoundException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Product product;

    @BeforeEach
    void init(){
        cart = new Cart();
        product = new Product("Apple", BigDecimal.TEN);
    }

    @Test
    void testAddProduct() {
        cart.addProduct(product);

        BigDecimal expected = BigDecimal.TEN;
        assertNotNull(cart.getProducts());
        assertEquals(expected, cart.getSum());
    }

    @Test
    void testRemoveProduct() {
        cart.addProduct(product);
        assertEquals(1, cart.getProducts().size());

        cart.removeProduct(product);

        int expected = 0;
        assertEquals(expected, cart.getProducts().size());
    }

    @Test
    void testAddProductNull(){
        assertThrows(NullPointerException.class, () -> cart.addProduct(null));
    }

    @Test
    void testNotHaveThisProductInListProducts(){
        cart.addProduct(product);
        assertEquals(1, cart.getProducts().size());

        cart.addProduct(new Product("Pizza", BigDecimal.TEN));
        assertEquals(2, cart.getProducts().size());

        cart.removeProduct(product);
        assertEquals(1, cart.getProducts().size());

        assertThrows(ProductNotFoundException.class, () ->cart.removeProduct(new Product("Burger", BigDecimal.ONE)));

    }

    @Test
    void testGenerateAnotherId(){
        Cart cart1 = new Cart();
        assertNotEquals(cart.getId(), cart1.getId());
    }
}
