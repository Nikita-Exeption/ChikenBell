package org.nikita;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DummyTest {

    @Test
    void dummyTest(){
        int actual = 2 + 2;
        int expected = 4;
        assertEquals(expected, actual);
    }
}
