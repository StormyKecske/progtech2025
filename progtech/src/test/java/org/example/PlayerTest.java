package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testGetName() {
        Player p = new Player("Teszt", 3);
        assertEquals("Teszt", p.getName());
    }

    @Test
    void testGetWins() {
        Player p = new Player("Teszt", 3);
        assertEquals(3, p.getWins());
    }
}
