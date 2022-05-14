package org.nikita.chiken_bell.core.service;

import org.nikita.chiken_bell.core.entity.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    Product create(String title, BigDecimal price);

    Product update(String id, String title, BigDecimal price);

    Optional<Product> getById(String id);

    Collection<Product> getAll();

    void deleteById(String id);
}
