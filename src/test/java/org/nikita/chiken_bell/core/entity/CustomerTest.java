package org.nikita.chiken_bell.core.entity;

import org.junit.jupiter.api.Test;
import org.nikita.chiken_bell.core.exception.PhoneEmptyException;
import org.nikita.chiken_bell.core.exception.PhoneIncorrectException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testInitCustomerWithCorrectPhone(){
        Customer customer = new Customer("Name", "123-456-78-90", "Adress");

        String expected = "Name";
        assertEquals(expected, customer.getName());
        assertEquals("Adress", customer.getAdress());
    }

    @Test
    void testInitCustomerWithIncorrectPhone(){
        assertThrows(PhoneIncorrectException.class, () -> new Customer("Name", "1234567"));
    }

    @Test
    void testInitCustomerWithEmptyPhone(){
        assertThrows(PhoneEmptyException.class, () -> new Customer("name", ""));
    }

    @Test
    void testInitCustomerWithoutAdress(){
        Customer customer = new Customer("Name", "123-456-78-90");

        assertNull(customer.getAdress());

        customer.setAdress("Adress");

        assertNotNull(customer.getAdress());
    }

}