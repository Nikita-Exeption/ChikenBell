package org.nikita.chiken_bell.core.entity;

import org.nikita.chiken_bell.core.exception.ProductNotFoundInListProductException;
import org.nikita.chiken_bell.core.exception.ProductNotHasInitialized;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Cart {

    private final String id;

    private BigDecimal sum = BigDecimal.ZERO;

    private final List<Product> products = new ArrayList<>();

    public Cart(){
        this.id = UUID.randomUUID().toString();
    }

    public void addProduct(Product product){
        if (product == null){
            throw new ProductNotHasInitialized();
        }
        sum = sum.add(product.getPrice());
        products.add(product);
    }

    public void removeProduct(Product product){
        if (products.contains(product)) {
            sum = sum.subtract(product.getPrice());
            products.remove(product);
        }else {
            throw new ProductNotFoundInListProductException();
        }
    }

    public List<Product> getProducts(){
        return Collections.unmodifiableList(products);
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getId() {
        return id;
    }
}
