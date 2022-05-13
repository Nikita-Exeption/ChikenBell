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
    }

}