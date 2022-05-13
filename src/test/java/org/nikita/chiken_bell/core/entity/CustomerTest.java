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
        Customer customer = new Customer("Name", "123-456-78-90", "Adress");

        assertEquals(NAME, customer.getName());
        assertEquals(ADRESS, customer.getAdress());
    }

    @Test
    void testInitCustomerWithIncorrectPHONE(){
        assertThrows(PhoneIncorrectException.class, () -> new Customer("Name", "1234567"));
    }

    @Test
    void testInitCustomerWithEmptyPHONE(){
        assertThrows(PhoneEmptyException.class, () -> new Customer("name", ""));
    }

    @Test
    void testInitCustomerWithoutADRESS(){
        Customer customer = new Customer("Name", "123-456-78-90");

        assertNull(customer.getAdress());

        customer.setAdress("Adress");

        assertNotNull(customer.getAdress());
    }

}