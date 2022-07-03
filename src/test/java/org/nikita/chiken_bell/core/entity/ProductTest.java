package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.exception.ProductNegativePriceException;
import org.nikita.chiken_bell.core.exception.ProductTitleBlankException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static final String TITLE = "Apple";

    @Test
    void testInitItem(){
        Product product = new Product(TITLE, BigDecimal.TEN);

        assertNotNull(product.getId());
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

    @Test
    void testInitProductWithTitleBlank(){
        assertThrows(ProductTitleBlankException.class, () -> new Product("", BigDecimal.TEN));
    }

    @Test
    void testInitProductWithNegativePrice(){
        assertThrows(ProductNegativePriceException.class, () -> new Product("Milk", BigDecimal.valueOf(-10)));
    }
}