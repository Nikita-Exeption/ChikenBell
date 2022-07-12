package org.nikita.chiken_bell.core.service.impl;

import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.exception.CustomerNotFoundException;
import org.nikita.chiken_bell.core.exception.PhoneAlreadyExistException;
import org.nikita.chiken_bell.core.exception.PhoneEmptyException;
import org.nikita.chiken_bell.core.service.CustomerService;

import java.util.*;

public class CustomerServiceImpl implements CustomerService {

    private final Set<Customer> customers = new HashSet<>();


    @Override
    public Customer create(String name, String phone, String address) {
        if (findByPhone(phone).isPresent()){
            throw new PhoneAlreadyExistException();
        }
        Customer customer = new Customer(name, phone, address);
        customers.add(customer);
        return customer;
    }

    @Override
    public Customer update(String id, String name, String phone, String address) {
        if (phone == null){
            throw new PhoneEmptyException();
        }
        if (findByPhone(phone).isPresent()){
            throw new PhoneAlreadyExistException();
        }
        Customer customer = getById(id).orElseThrow(CustomerNotFoundException::new);
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAddress(address);
        return customer;
    }

    @Override
    public Optional<Customer> getById(String id) {
        return customers.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        return customers.stream().filter(x -> x.getPhone().equals(phone)).findFirst();
    }

    @Override
    public Collection<Customer> getAll() {
        return Collections.unmodifiableSet(customers);
    }

    @Override
    public void deleteById(String id) {
        customers.remove(getById(id).orElseThrow(CustomerNotFoundException::new));
    }
}
