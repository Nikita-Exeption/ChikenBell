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
    void testInitEmptyCart(){
        cart = new Cart();
        assertNotNull(cart.getId());
        assertEquals(BigDecimal.ZERO, cart.getSum());
        assertEquals(0, cart.getProducts().size());
    }

    @Test
    void testAddProduct() {
        cart.addProduct(product);

        assertNotNull(cart.getProducts());
        assertEquals(product.getPrice(), cart.getSum());
    }

    @Test
    void testRemoveProduct() {
        cart.addProduct(product);
        assertEquals(1, cart.getProducts().size());
        assertEquals(product.getPrice(), cart.getSum());

        cart.removeProduct(product);

        int expected = 0;
        assertEquals(expected, cart.getProducts().size());
        assertEquals(BigDecimal.ZERO, cart.getSum());
    }

    @Test
    void testAddProductNull(){
        assertThrows(NullPointerException.class, () -> cart.addProduct(null));
    }

    @Test
    void testNotHaveThisProductInListProducts(){
        assertThrows(ProductNotFoundException.class, () -> cart.removeProduct(new Product("Burger", BigDecimal.ONE)));
    }

    @Test
    void testGenerateAnotherId(){
        Cart cart1 = new Cart();
        assertNotEquals(cart.getId(), cart1.getId());
    }
}
