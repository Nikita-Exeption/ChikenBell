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
        checkProduct(title, price);
        Product product = new Product(title, price);
        products.add(product);
        return product;
    }

    @Override
    public Product update(String id, String title, BigDecimal price) {
        Product product = getById(id).orElseThrow(ProductNotFoundException::new);
        products.remove(product);
        product.setTitle(title);
        product.setPrice(price);
        products.add(product);
        return product;
    }

    @Override
    public Optional<Product> getById(String id) {
        return products.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Product> getAll() {
        return getProducts();
    }

    @Override
    public void deleteById(String id) {
        products.remove(getById(id).orElseThrow(ProductNotFoundException::new));
    }

    private Set<Product> getProducts(){
        return Collections.unmodifiableSet(products);
    }

    private void checkProduct(String title, BigDecimal price){
        if (title.isBlank() || price == null){
            throw new NullPointerException();
        }
        for (Product p : products){
            if (p.getTitle().equals(title)){
                throw new ProductUniqueException();
            }
        }
    }
}
