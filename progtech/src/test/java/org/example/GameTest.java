package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {

    private Game game;
    private Board board;
    private Database database;
    private Scanner mockScanner;
    private Random mockRandom;

    @BeforeEach
    void setup() {
        database = mock(Database.class);
        mockScanner = mock(Scanner.class);
        mockRandom = mock(Random.class);
        board = new Board(10, 10);

        game = new Game(database, mockScanner, mockRandom, board);
    }

    private boolean invokeCheckWin(String symbol) throws Exception {
        Method method = Game.class.getDeclaredMethod("checkWin", String.class);
        method.setAccessible(true);
        return (boolean) method.invoke(game, symbol);
    }

    @Test
    void testHorizontalWin() throws Exception {
        board.placeSymbol(0, 0, "X");
        board.placeSymbol(0, 1, "X");
        board.placeSymbol(0, 2, "X");
        board.placeSymbol(0, 3, "X");

        assertTrue(invokeCheckWin("X"));
    }

    @Test
    void testVerticalWin() throws Exception {
        board.placeSymbol(0, 0, "O");
        board.placeSymbol(1, 0, "O");
        board.placeSymbol(2, 0, "O");
        board.placeSymbol(3, 0, "O");

        assertTrue(invokeCheckWin("O"));
    }

    @Test
    void testDiagonalWinDownRight() throws Exception {
        board.placeSymbol(0, 0, "X");
        board.placeSymbol(1, 1, "X");
        board.placeSymbol(2, 2, "X");
        board.placeSymbol(3, 3, "X");

        assertTrue(invokeCheckWin("X"));
    }

    @Test
    void testDiagonalWinDownLeft() throws Exception {
        board.placeSymbol(0, 3, "O");
        board.placeSymbol(1, 2, "O");
        board.placeSymbol(2, 1, "O");
        board.placeSymbol(3, 0, "O");

        assertTrue(invokeCheckWin("O"));
    }

    @Test
    void testNoWin() throws Exception {
        board.placeSymbol(0, 0, "X");
        board.placeSymbol(0, 1, "O");
        board.placeSymbol(0, 2, "X");
        board.placeSymbol(0, 3, "O");

        assertFalse(invokeCheckWin("X"));
    }

    @Test
    void testHumanMoveInvalidThenValid() throws Exception {
        when(mockScanner.next()).thenReturn("abc", "5");

        Method method = Game.class.getDeclaredMethod("readCoordinate", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(game, "sor");

        assertEquals(5, result);
    }

    @Test
    void testComputerMovePlacesSymbol() {
        when(mockRandom.nextInt(10)).thenReturn(0);

        board.placeSymbol(0, 0, "O");

        assertEquals("O", board.getSymbolAt(0, 0));
    }

    @Test
    void testDatabaseAddWinCalledWhenHumanWins() {
        when(mockScanner.next()).thenReturn("1", "1");

        board.placeSymbol(0, 1, "X");
        board.placeSymbol(0, 2, "X");
        board.placeSymbol(0, 3, "X");

        when(mockRandom.nextInt(10)).thenReturn(5);

        game.start();

        verify(database, times(1)).addWin("TestPlayer");
    }

    @Test
    void testReadCoordinateHandlesInvalidInputGracefully() throws Exception {
        // Hibás bemenetek: nem szám, túl nagy, túl kicsi
        when(mockScanner.next()).thenReturn("alma", "15", "0", "5");

        Method method = Game.class.getDeclaredMethod("readCoordinate", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(game, "sor");

        assertEquals(5, result);
    }

}
