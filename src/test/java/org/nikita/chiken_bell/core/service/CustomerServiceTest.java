package org.nikita.chiken_bell.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.exception.CustomerNotFoundException;
import org.nikita.chiken_bell.core.exception.CustomerPhoneUniqueException;
import org.nikita.chiken_bell.core.service.impl.CustomerServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private final CustomerService service = new CustomerServiceImpl();

    private Customer customer;

    private static final String NAME = "Name";
    private static final String PHONE = "123-456-78-90";
    private static final String ADDRESS = "Address";

    @BeforeEach
    void initCustomer(){
        customer = service.create(NAME, PHONE, ADDRESS);
    }

    @Test
    void testCreateWithoutAddress() {
        Customer cus = service.create(NAME, "012-345-67-90");

        assertNotNull(cus.getId());
        assertEquals(NAME, cus.getName());
        assertEquals("012-345-67-90", cus.getPhone());
        assertNull(cus.getAddress());
    }

    @Test
    void testCreateWithSamePhone(){
        assertThrows(CustomerPhoneUniqueException.class, () -> service.create(NAME, PHONE));
    }

    @Test
    void testCreateWithAddress() {
        assertNotNull(customer.getId());
        assertEquals(NAME, customer.getName());
        assertEquals(PHONE, customer.getPhone());
        assertEquals(ADDRESS, customer.getAddress());
    }

    @Test
    void testUpdateWithoutAddress() {
        assertEquals(NAME, customer.getName());
        assertEquals(PHONE, customer.getPhone());
        assertEquals(ADDRESS, customer.getAddress());

        Customer update = service.update(customer.getId(), "Nik", "012-345-67-89");

        assertEquals(customer.getId(), update.getId());
        assertEquals("Nik", update.getName());
        assertEquals("012-345-67-89", update.getPhone());
        assertEquals(ADDRESS, update.getAddress());
    }

    @Test
    void testUpdateWithAddress() {
        assertEquals(NAME, customer.getName());
        assertEquals(PHONE, customer.getPhone());
        assertEquals(ADDRESS, customer.getAddress());

        Customer update = service.update(customer.getId(), "Nik", "012-345-67-89", "Street");

        assertEquals(customer.getId(), update.getId());
        assertEquals("Nik", update.getName());
        assertEquals("012-345-67-89", update.getPhone());
        assertEquals("Street", update.getAddress());
    }

    @Test
    void getById() {
        Customer get = service.getById(customer.getId()).orElseThrow(CustomerNotFoundException::new);

        assertEquals(get.getId(), customer.getId());
        assertEquals(NAME, get.getName());
        assertEquals(PHONE, get.getPhone());
        assertEquals(ADDRESS, get.getAddress());
    }

    @Test
    void getCustomerNotFound(){
        assertThrows(CustomerNotFoundException.class, () -> service.deleteById("456-098-56-78"));
    }

    @Test
    void getByPhone() {
        Customer getByPhone = service.getByPhone(customer.getPhone()).orElseThrow(CustomerNotFoundException::new);

        assertEquals(customer.getId(), getByPhone.getId());
        assertEquals(NAME, getByPhone.getName());
        assertEquals(PHONE, getByPhone.getPhone());
        assertEquals(ADDRESS, getByPhone.getAddress());
    }

    @Test
    void getAll() {
        assertEquals(1, service.getAll().size());

        service.create("Mike", "321-456-78-09");

        assertEquals(2, service.getAll().size());
    }

    @Test
    void deleteById() {
        assertEquals(1, service.getAll().size());

        service.deleteById(customer.getId());

        assertEquals(0, service.getAll().size());
    }
}