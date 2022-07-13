package org.nikita.chiken_bell.core.service;

import org.nikita.chiken_bell.core.entity.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerService {

    Customer create(String name, String phone, String address);

    Customer update(String id, String name, String phone, String address);

    Optional<Customer> getById(String id);

    Optional<Customer> findByPhone(String phone);

    Collection<Customer> getAll();

    void deleteById(String id);
}
