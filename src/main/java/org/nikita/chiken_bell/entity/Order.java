package org.nikita.chiken_bell.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private BigDecimal sum;

    private List<Product> products;

    public Order(Cart cart, Customer customer, boolean isDelivery){
        if (isDelivery && (customer.getAdress() == null || customer.getAdress().isBlank())){
            throw new UnsupportedOperationException();
        }
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

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", products=" + products +
                '}';
    }
}
