package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.exception.PhoneEmptyException;
import org.nikita.chiken_bell.core.exception.PhoneIncorrectException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private static final String NAME = "Name";
    private static final String PHONE = "123-456-78-90";
    private static final String ADRESS = "Adress";

    @Test
    void testInitCustomerWithCorrectPHONE(){
        Customer customer = new Customer(NAME, PHONE, ADRESS);

        assertNotNull(customer.getId());
        assertEquals(NAME, customer.getName());
        assertEquals(ADRESS, customer.getAdress());
        assertEquals(PHONE, customer.getPhone());
    }

    @Test
    void testInitCustomerWithIncorrectPHONE(){
        assertThrows(PhoneIncorrectException.class, () -> new Customer(NAME, "123425436"));
    }

    @Test
    void testInitCustomerWithEmptyPHONE(){
        assertThrows(PhoneEmptyException.class, () -> new Customer(NAME, ""));
    }

    @Test
    void testInitCustomerWithoutADRESS(){
        Customer customer = new Customer(NAME, PHONE);

        assertNull(customer.getAdress());

        customer.setAdress(ADRESS);

        assertNotNull(customer.getAdress());
        assertEquals(ADRESS, customer.getAdress());
    }

    @Test
    void testEqualsSameObjects(){
        Customer customer = new Customer(NAME, PHONE);
        assertSame(customer, customer);
    }

    @Test
    void testEqualsNotSameObjects(){
        Customer customer = new Customer(NAME, PHONE);
        Customer customer1 = new Customer("Jack", "012-345-67-89");
        assertNotSame(customer, customer1);
    }

}