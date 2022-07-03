package org.nikita.chiken_bell.core.entity;

import org.nikita.chiken_bell.core.exception.PhoneEmptyException;
import org.nikita.chiken_bell.core.exception.PhoneIncorrectException;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private final String id;

    private String name;

    private String phone;

    private String address;

    public Customer(String name, String phone){
        checkPhone(phone);
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
    }

    public Customer(String name, String phone, String address){
        this(name, phone);
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private void checkPhone(String phone){
        if(phone.isBlank()){
            throw new PhoneEmptyException();
        }
        if(!phone.matches("[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}")){
            throw new PhoneIncorrectException();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

}
