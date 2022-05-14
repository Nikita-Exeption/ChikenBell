package org.nikita.chiken_bell.core.service.impl;

import org.nikita.chiken_bell.core.entity.Product;
import org.nikita.chiken_bell.core.exception.ProductNotFoundException;
import org.nikita.chiken_bell.core.exception.ProductUniqueException;
import org.nikita.chiken_bell.core.service.ProductService;

import java.math.BigDecimal;
import java.util.*;

public class ProductServiceImpl implements ProductService {

    private final Set<Product> products = new HashSet<>();

    @Override
    public Product create(String title, BigDecimal price) {
        checkProduct(title);
        Product product = new Product(title, price);
        products.add(product);
        return product;
    }

    @Override
    public Product update(String id, String title, BigDecimal price) {
        Product product = getById(id).orElseThrow(ProductNotFoundException::new);
        product.setTitle(title);
        product.setPrice(price);
        return product;
    }

    @Override
    public Optional<Product> getById(String id) {
        return products.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Product> getByTitle(String title) {
        return products.stream().filter(x -> x.getTitle().equals(title)).findFirst();
    }

    @Override
    public Collection<Product> getAll() {
        return Collections.unmodifiableSet(products);
    }

    @Override
    public void deleteById(String id) {
        products.remove(getById(id).orElseThrow(ProductNotFoundException::new));
    }

    private void checkProduct(String title){
        if (getByTitle(title).isPresent()){
            throw new ProductUniqueException();
        }
    }
}
