package org.nikita.chiken_bell.core.entity;

import org.nikita.chiken_bell.core.exception.CustomerAdressEmptyException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final String id;

    private final BigDecimal sum;

    private final List<Product> products;

    public Order(Cart cart, Customer customer, boolean isDelivery){
        checkAddress(customer.getAdress(), isDelivery);
        this.id = UUID.randomUUID().toString();
        this.sum = cart.getSum();
        this.products = cart.getProducts();
    }

    public BigDecimal getSum() {
        return sum;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private void checkAddress(String address, boolean isDelivery){
        if (isDelivery && (address == null || address.isBlank())){
            throw new CustomerAdressEmptyException();
        }
    }
}
