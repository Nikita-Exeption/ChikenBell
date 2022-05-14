package org.nikita.chiken_bell.core.entity;

import org.nikita.chiken_bell.core.exception.CustomerAdressEmptyException;
import org.nikita.chiken_bell.core.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private final String id;

    private BigDecimal sum = BigDecimal.ZERO;

    private String address;

    private final List<Product> products = new ArrayList<>();

    public Cart(){
        this.id = UUID.randomUUID().toString();
    }

    public Cart(String address){
        this();
        checkAddress(address);
        this.address = address;
    }

    public void addProduct(Product product){
        if (product == null){
            throw new NullPointerException();
        }
        sum = sum.add(product.getPrice());
        products.add(product);
    }

    public void removeProduct(Product product){
        if (products.contains(product)) {
            sum = sum.subtract(product.getPrice());
            products.remove(product);
        }else {
            throw new ProductNotFoundException();
        }
    }

    public void setAddress(String address){
        checkAddress(address);
        this.address = address;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", sum=" + sum +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public String getAddress() {
        return address;
    }

    private void checkAddress(String address){
        if (address.isBlank()){
            throw new CustomerAdressEmptyException();
        }
    }
}
