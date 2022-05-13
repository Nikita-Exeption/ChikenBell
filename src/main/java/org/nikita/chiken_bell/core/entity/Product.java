package org.nikita.chiken_bell.core.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {

    private final String id;

    private String title;

    private BigDecimal price;

    public Product(String title, BigDecimal price){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
