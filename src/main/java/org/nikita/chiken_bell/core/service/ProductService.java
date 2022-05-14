package org.nikita.chiken_bell.core.service;

import org.nikita.chiken_bell.core.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(Product product);

    Optional<Product> findById(String  id);

    List<Product> findAll();

    void deleteById(String id);
}
