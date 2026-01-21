package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void sampleTest() {
        int actual = 2 + 2;
        int expected = 4;
        assertEquals(expected, actual);
    }
}
