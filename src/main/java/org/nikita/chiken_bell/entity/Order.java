package org.nikita.chiken_bell.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Order {

    private BigDecimal sum;

    private List<Product> products;

    public Order(Cart cart){
        this.sum = counterSum(cart.getProducts());
        this.products = Collections.unmodifiableList(cart.getProducts());
    }

    private BigDecimal counterSum(List<Product> products){
        BigDecimal sum = BigDecimal.ZERO;
        for (Product p : products){
            sum = sum.add(p.getPrice());
        }
        return sum;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public List<Product> getProducts() {
        return products;
    }
}
