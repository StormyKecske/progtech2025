package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void placeSymbolPlacesCorrectly() {
        Board board = new Board(3, 3);

        boolean result = board.placeSymbol(0, 0, "X");

        assertTrue(result);
        assertEquals("X", board.getSymbolAt(0, 0));
    }

    @Test
    void placeSymbolFailsIfCellNotEmpty() {
        Board board = new Board(3, 3);
        board.placeSymbol(0, 0, "X");

        boolean result = board.placeSymbol(0, 0, "O");

        assertFalse(result);
        assertEquals("X", board.getSymbolAt(0, 0));
    }

    @Test
    void placeSymbolFailsIfSymbolIsNull() {
        Board board = new Board(3, 3);

        boolean result = board.placeSymbol(0, 0, null);

        assertFalse(result);
        assertNull(board.getSymbolAt(0, 0));
    }

    @Test
    void isFullReturnsFalseOnEmptyBoard() {
        Board board = new Board(3, 3);

        assertFalse(board.isFull());
    }

    @Test
    void isFullReturnsTrueWhenBoardIsFull() {
        Board board = new Board(2, 2);
        board.placeSymbol(0, 0, "X");
        board.placeSymbol(0, 1, "O");
        board.placeSymbol(1, 0, "X");
        board.placeSymbol(1, 1, "O");

        assertTrue(board.isFull());
    }

    @Test
    void getSymbolAtReturnsNullForEmptyCell() {
        Board board = new Board(3, 3);

        assertNull(board.getSymbolAt(1, 1));
    }
}
