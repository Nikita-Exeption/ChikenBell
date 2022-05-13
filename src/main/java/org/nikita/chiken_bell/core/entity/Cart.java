package org.nikita.chiken_bell.core.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

    private BigDecimal sum = BigDecimal.ZERO;

    private final List<Product> products = new ArrayList<>();

    public List<Product> addProduct(Product product){
        sum = sum.add(product.getPrice());
        products.add(product);
        return Collections.unmodifiableList(products);
    }

    public void removeProduct(Product product){
        sum = sum.subtract(product.getPrice());
        products.remove(product);
    }

    public List<Product> getProducts(){
        return Collections.unmodifiableList(products);
    }

    public BigDecimal getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "sum=" + sum +
                ", products=" + products +
                '}';
    }
}
