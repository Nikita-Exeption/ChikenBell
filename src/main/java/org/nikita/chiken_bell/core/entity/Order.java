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

    private final String deliveryAddress;

    private final boolean isDelivery;

    private OrderStatus status;

    private final List<Product> products;

    public Order(Cart cart, Customer customer, boolean isDelivery){
        this.isDelivery = isDelivery;
        checkAddress(customer.getAddress());
        this.id = UUID.randomUUID().toString();
        this.sum = cart.getSum();
        this.deliveryAddress = customer.getAddress();
        this.products = cart.getProducts();
        this.status = OrderStatus.NEW;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public String getId() {
        return id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setOrderStatus(OrderStatus status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", sum=" + sum +
                ", products=" + products +
                '}';
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

    private void checkAddress(String address){
        if (isDelivery() && (address == null || address.isBlank())){
            throw new CustomerAdressEmptyException();
        }
    }
}
