package org.nikita.chiken_bell.core.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Product;
import org.nikita.chiken_bell.core.exception.ProductNotFoundException;
import org.nikita.chiken_bell.core.exception.ProductUniqueException;
import org.nikita.chiken_bell.core.service.ProductService;
import org.nikita.chiken_bell.core.service.impl.ProductServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final ProductService service = new ProductServiceImpl();
    private Product prod;

    private static final String TITLE = "Apple";

    @BeforeEach
    void initSet() {
        prod = service.create("Milk", BigDecimal.TEN);
    }

    @Test
    void testCreate() {
        assertEquals(1, service.getAll().size());
        Product product = service.create(TITLE, BigDecimal.TEN);

        assertNotNull(service.getById(product.getId()));
        assertEquals(2, service.getAll().size());
    }

    @Test
    void testUpdate() {
        Product product = service.create(TITLE, BigDecimal.ONE);
        Product update = service.update(product.getId(), "Update", BigDecimal.TEN);

        Product get = service.getById(product.getId()).orElseThrow(ProductNotFoundException::new);

        assertEquals(product.getId(), update.getId());

        assertEquals(update.getTitle(), get.getTitle());
        assertEquals(update.getPrice(), get.getPrice());
    }

    @Test
    void testGetById() {
            Product product = service.getById(prod.getId()).orElseThrow(ProductNotFoundException::new);

            assertEquals(prod.getTitle(), product.getTitle());
            assertEquals(prod.getPrice(), product.getPrice());
    }

    @Test
    void testGetAll() {
        assertEquals(1, service.getAll().size());

        service.create(TITLE, BigDecimal.TEN);

        assertEquals(2, service.getAll().size());
    }

    @Test
    void deleteById() {
        assertEquals(1, service.getAll().size());

        service.deleteById(prod.getId());

        assertEquals(0, service.getAll().size());

    }

    @Test
    void testInitProductServiceWithNull(){
        assertThrows(NullPointerException.class, () -> service.create(null, BigDecimal.TEN));
    }

    @Test
    void testUniqueProductTitle(){
        assertThrows(ProductUniqueException.class, () -> service.create("Milk", BigDecimal.TEN));
    }
}