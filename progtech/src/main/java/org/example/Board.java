package org.example;

public class Board {
    private final String[][] grid;
    private final int rows;
    private final int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new String[rows][cols];
    }

    public boolean placeSymbol(int row, int col, String symbol) {
        if (grid[row][col] == null && symbol != null) {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] == null ? "." : grid[r][c]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public String getSymbolAt(int row, int col) {
        return grid[row][col];
    }
}
