package org.nikita.chiken_bell.core.service.impl;

import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.exception.NotFoundCustomerException;
import org.nikita.chiken_bell.core.service.CustomerService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private final Set<Customer> customers = new HashSet<>();

    @Override
    public Customer create(String name, String phone, String address) {
        Customer customer = create(name, phone);
        customer.setAddress(address);
        customers.add(customer);
        return customer;
    }

    @Override
    public Customer create(String name, String phone) {
        Customer customer = new Customer(name, phone);
        customers.add(customer);
        return customer;
    }


    @Override
    public Customer update(String id, String name, String phone, String address) {
        Customer customer = getById(id).orElseThrow(NotFoundCustomerException::new);
        deleteById(id);
        customer.setName(name);
        customer.setPhone(name);
        customer.setAddress(address);
        customers.add(customer);
        return customer;
    }

    @Override
    public Optional<Customer> getById(String id) {
        return Optional.of(customers.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(NotFoundCustomerException::new));
    }

    @Override
    public Set<Customer> getAll() {
        return Collections.unmodifiableSet(customers);
    }

    @Override
    public void deleteById(String id) {
        customers.remove(getById(id).orElseThrow(NotFoundCustomerException::new)) ;
    }
}
