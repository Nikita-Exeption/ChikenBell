package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static final String TITLE = "Apple";

    @Test
    void testInitItem(){
        Product product = new Product(TITLE, BigDecimal.TEN);

        assertEquals(TITLE, product.getTitle());
        assertEquals(BigDecimal.TEN, product.getPrice());

        product.setPrice(BigDecimal.ONE);
        product.setTitle("Title");

        assertEquals(product.getPrice(), BigDecimal.ONE);
        assertEquals(product.getTitle(), "Title");
    }

    @Test
    void testGenerateAnotherId(){
        Product product = new Product(TITLE, BigDecimal.ONE);
        Product product1 = new Product("Potato", BigDecimal.ONE);
        assertEquals(product.getId(), product.getId());
        assertNotEquals(product.getId(), product1.getId());
    }
}