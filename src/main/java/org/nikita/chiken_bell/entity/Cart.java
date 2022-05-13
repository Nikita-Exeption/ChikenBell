package org.nikita.chiken_bell.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private BigDecimal sum = BigDecimal.ZERO;

    private List<Product> products = new ArrayList<>();

    public List<Product> addProduct(Product product){
        sum = sum.add(product.getPrice());
        products.add(product);
        return products;
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public List<Product> getProducts(){
        return products;
    }

    public BigDecimal getSum() {
        return sum;
    }

}
