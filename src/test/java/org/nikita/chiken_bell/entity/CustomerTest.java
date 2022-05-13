package org.nikita.chiken_bell.entity;

import org.junit.jupiter.api.Test;

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
        assertThrows(UnsupportedOperationException.class, () -> new Customer("Name", "1234567"));
    }

    @Test
    void testInitCustomerWithEmptyPhone(){
        assertThrows(UnsupportedOperationException.class, () -> new Customer("name", ""));
    }

    @Test
    void testInitCustomerWithoutAdress(){
        Customer customer = new Customer("Name", "123-456-78-90");

        assertNull(customer.getAdress());

        customer.setAdress("Adress");

        assertNotNull(customer.getAdress());
    }

}