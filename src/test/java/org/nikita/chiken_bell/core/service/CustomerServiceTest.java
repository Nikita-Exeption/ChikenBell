package org.nikita.chiken_bell.core.service;

import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.exception.NotFoundCustomerException;
import org.nikita.chiken_bell.core.service.impl.CustomerServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerServiceTest {

    private final CustomerService service = new CustomerServiceImpl();

    private static final String NAME = "Name";
    private static final String PHONE = "123-456-78-90";
    private static final String ADDRESS = "Address";

    @Test
    void testCreate() {
        Customer customer = service.create(NAME, PHONE, ADDRESS);

        assertNotNull(customer.getId());
        assertEquals(NAME, customer.getName());
        assertEquals(PHONE, customer.getPhone());
        assertEquals(ADDRESS, customer.getAddress());
    }

    @Test
    void testUpdate() {
        Customer customer = service.create(NAME, PHONE);

        assertNotNull(customer.getId());
        assertEquals(NAME, customer.getName());

        Customer get = service.update(customer.getId(), "Nik", "123-456-78-90", "Street");

        assertEquals(customer.getId(), get.getId());
        assertEquals("Nik", get.getName());
        assertEquals("Street", get.getAddress());

    }

    @Test
    void testGetById() {
        Customer customer = service.create(NAME, PHONE, ADDRESS);

        assertNotNull(customer.getId());

        Customer get = service.getById(customer.getId()).orElseThrow(NotFoundCustomerException::new);

        assertEquals(customer.getId(), customer.getId());
        assertEquals(customer.getName(), get.getName());
        assertEquals(customer.getPhone(), get.getPhone());
        assertEquals(customer.getAddress(), get.getAddress());

    }

    @Test
    void getAll() {
        assertEquals(0, service.getAll().size());

        service.create(NAME, PHONE, ADDRESS);

        assertEquals(1, service.getAll().size());
    }

    @Test
    void deleteById() {
        Customer customer = service.create(NAME, PHONE);

        assertEquals(1, service.getAll().size());

        service.deleteById(customer.getId());

        assertEquals(0, service.getAll().size());
    }
}