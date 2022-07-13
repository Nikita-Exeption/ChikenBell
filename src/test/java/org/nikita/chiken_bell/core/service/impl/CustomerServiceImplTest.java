package org.nikita.chiken_bell.core.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.exception.PhoneAlreadyExistException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    private final CustomerServiceImpl service = new CustomerServiceImpl();
    private Customer customer;

    private static final String NAME = "Mike";
    private static final String PHONE = "089-909-43-56";
    private static final String ADDRESS = "Manchester";

    @BeforeEach
    void before(){
        customer = service.create(NAME, PHONE, ADDRESS);
    }

    @Test
    void testCreate() {
        assertEquals(1, service.getAll().size());

        String phone = "123-456-76-53";
        Customer newCustomer = service.create(NAME, phone, ADDRESS);

        assertEquals(2, service.getAll().size());
        assertEquals(NAME, newCustomer.getName());
        assertEquals(phone, newCustomer.getPhone());
        assertEquals(ADDRESS, newCustomer.getAddress());

    }

    @Test
    void testCreateExistPhone(){
        assertThrows(PhoneAlreadyExistException.class, () -> service.create(NAME, PHONE, ADDRESS));
    }

    @Test
    void testUpdate() {
        String newName = "Cristiano";
        String newPhone = "123-456-78-90";
        String newAddress = "London";

        Customer updateCustomer = service.update(customer.getId(), newName, newPhone, newAddress);

        assertEquals(customer.getId(), updateCustomer.getId());
        assertEquals(newName, updateCustomer.getName());
        assertEquals(newAddress, updateCustomer.getAddress());
        assertEquals(newPhone, updateCustomer.getPhone());
    }

    @Test
    void testGetById() {
        Optional<Customer> getOptionalCustomer = service.getById(customer.getId());

        assertTrue(getOptionalCustomer.isPresent());

        Customer getCustomer = getOptionalCustomer.get();

        assertEquals(customer.getId(), getCustomer.getId());
        assertEquals(customer.getName(), getCustomer.getName());
        assertEquals(customer.getPhone(), getCustomer.getPhone());
        assertEquals(customer.getAddress(), getCustomer.getAddress());
    }

    @Test
    void testGetByPhone() {
        Optional<Customer> getOptionalCustomer = service.findByPhone(customer.getPhone());

        assertTrue(getOptionalCustomer.isPresent());

        Customer getCustomer = getOptionalCustomer.get();

        assertEquals(customer.getId(), getCustomer.getId());
        assertEquals(customer.getName(), getCustomer.getName());
        assertEquals(customer.getPhone(), getCustomer.getPhone());
        assertEquals(customer.getAddress(), getCustomer.getAddress());
    }

    @Test
    void testGetAll() {
        assertEquals(Set.of(customer), service.getAll());
        assertEquals(1, service.getAll().size());

        Customer newCustomer = service.create(NAME, "123-456-87-98", "London");

        assertEquals(Set.of(customer, newCustomer), service.getAll());
        assertEquals(2, service.getAll().size());
    }

    @Test
    void deleteById() {
        assertEquals(1, service.getAll().size());

        service.deleteById(customer.getId());

        assertEquals(0, service.getAll().size());
    }
}