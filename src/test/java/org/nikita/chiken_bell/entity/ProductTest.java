package org.nikita.chiken_bell.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testInitItem(){
        Product product = new Product("Apple", BigDecimal.TEN);

        String expected = "Apple";
        assertEquals(expected, product.getTitle());
        assertEquals(BigDecimal.TEN, product.getPrice());
    }

}