package org.nikita.chiken_bell.core.service;

import org.nikita.chiken_bell.core.entity.Customer;

import java.util.Optional;
import java.util.Set;

public interface CustomerService {

    Customer create(String name, String phone, String address);

    Customer create(String name, String phone);

    Customer update(String id, String name, String phone, String address);

    Optional<Customer> getById(String id);

    Set<Customer> getAll();

    void deleteById(String id);
}
