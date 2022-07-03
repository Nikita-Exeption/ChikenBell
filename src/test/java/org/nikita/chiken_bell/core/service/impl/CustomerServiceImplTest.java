package org.nikita.chiken_bell.core.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.entity.Customer;
import org.nikita.chiken_bell.core.exception.CustomerNotFoundException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerServiceImplTest {

    private Customer customer;
    private final CustomerServiceImpl customerService = new CustomerServiceImpl();

    private static final String NAME = "Nikita";
    private static final String PHONE = "123-456-45-45";
    private static final String ADDRESS = "Street";

    @BeforeEach
    void clearCollection(){
       customer = customerService.create(NAME, PHONE);
    }

    @Test
    void createTestWithNameAndPhone() {
        assertEquals(1, customerService.getAll().size());

        customerService.create(NAME, PHONE);

        assertEquals(2, customerService.getAll().size());
    }

    @Test
    void createTestWithNameAndPhoneAndAddress() {
        assertEquals(1, customerService.getAll().size());

        customerService.create(NAME, PHONE, ADDRESS);

        assertEquals(2, customerService.getAll().size());
    }

    @Test
    void updateTestNameAndPhone() {
        String newName = "NewName";
        String newPhone = "123-456-78-90";

        Customer newCustomer = customerService.update(customer.getId(), newName, newPhone);

        assertEquals(newCustomer.getId(), customer.getId());
        assertEquals(newCustomer.getPhone(), newPhone);
        assertEquals(newCustomer.getName(), newName);
    }

    @Test
    void updateTestNameAndPhoneAndAddress() {
       String newName = "NewName";
       String newPhone = "321-456-70-82";
       String newAddress = "Manchester";

       assertEquals(1, customerService.getAll().size());
       Customer newCustomer = customerService.update(customer.getId(), newName, newPhone, newAddress);
       assertEquals(1, customerService.getAll().size());

       assertEquals(customer.getId(), newCustomer.getId());
       assertEquals(customer.getName(), newName);
       assertEquals(customer.getPhone(), newPhone);
       assertEquals(newCustomer.getAddress(), newAddress);
    }

    @Test
    void getByIdTestPositiveCase() {
        Customer getCustomer = customerService.getById(customer.getId()).orElseThrow(CustomerNotFoundException::new);

        assertEquals(customer.getId(), getCustomer.getId());
        assertEquals(customer.getName(), getCustomer.getName());
        assertEquals(customer.getPhone(), getCustomer.getPhone());
    }

    @Test
    void getByIdTestNegativeCase(){
        assertThrows(CustomerNotFoundException.class, () -> customerService.getById("Nothing").orElseThrow(CustomerNotFoundException::new));
    }

    @Test
    void getAllTest() {
        assertEquals(1, customerService.getAll().size());
        assertEquals(customerService.getAll(), Set.of(customer));

        customerService.create(NAME, PHONE);

        assertEquals(2, customerService.getAll().size());

        customerService.create(NAME, PHONE, ADDRESS);

        assertEquals(3, customerService.getAll().size());
    }

    @Test
    void deleteById() {
        assertEquals(1, customerService.getAll().size());

        customerService.deleteById(customer.getId());

        assertEquals(0, customerService.getAll().size());
    }
}