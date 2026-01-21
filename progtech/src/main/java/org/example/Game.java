package org.example;

import java.util.Scanner;
import java.util.Random;

public class Game {

    private final Player human;
    private final Player computer;
    private final Board board;
    private final Scanner scanner;
    private final Random random;
    private final Database database;

    // EREDETI KONSTRUKTOR (normál játékhoz)
    public Game(Database database) {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.database = database;

        System.out.print("Add meg a játékos nevét: ");
        String name = scanner.nextLine();

        this.human = new Player(name, 0);
        this.computer = new Player("Gép", 0);
        this.board = new Board(10, 10);
    }

    // TESZTBARÁT KONSTRUKTOR
    public Game(Database database, Scanner scanner, Random random, Board board) {
        this.database = database;
        this.scanner = scanner;
        this.random = random;
        this.board = board;

        this.human = new Player("TestPlayer", 0);
        this.computer = new Player("Gép", 0);
    }

    public void start() {
        System.out.println("A játék elindult!");
        board.printBoard();

        Player current = human;

        while (!board.isFull()) {
            System.out.println(current.getName() + " következik.");

            int row, col;

            if (current == human) {
                row = readCoordinate("sort") - 1;
                col = readCoordinate("oszlop") - 1;
            } else {
                do {
                    row = random.nextInt(10);
                    col = random.nextInt(10);
                } while (board.getSymbolAt(row, col) != null);

                System.out.println("Gép lépett: " + (row + 1) + "," + (col + 1));
            }

            String symbol = (current == human) ? "X" : "O";

            if (board.placeSymbol(row, col, symbol)) {
                board.printBoard();

                if (checkWin(symbol)) {
                    System.out.println("Gratulálok, " + current.getName() + " nyert!");

                    if (current == human) {
                        database.addWin(human.getName());
                    }

                    return;
                }

                current = (current == human) ? computer : human;

            } else {
                System.out.println("Ez a mező már foglalt, próbáld újra!");
            }
        }

        System.out.println("A tábla megtelt, döntetlen!");
    }

    private int readCoordinate(String name) {
        while (true) {
            System.out.print("Add meg az " + name + " (1-10): ");
            String input = scanner.next();

            try {
                int value = Integer.parseInt(input);
                if (value >= 1 && value <= 10) {
                    return value;
                } else {
                    System.out.println("Hibás szám! Csak 1 és 10 között lehet.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ez nem szám! Próbáld újra.");
            }
        }
    }

    private boolean checkWin(String symbol) {
        int size = 10;
        int winLength = 4;

        // Sorok
        for (int r = 0; r < size; r++) {
            for (int c = 0; c <= size - winLength; c++) {
                boolean win = true;
                for (int k = 0; k < winLength; k++) {
                    if (!symbol.equals(board.getSymbolAt(r, c + k))) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        // Oszlopok
        for (int c = 0; c < size; c++) {
            for (int r = 0; r <= size - winLength; r++) {
                boolean win = true;
                for (int k = 0; k < winLength; k++) {
                    if (!symbol.equals(board.getSymbolAt(r + k, c))) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        // Átlók (bal felső → jobb alsó)
        for (int r = 0; r <= size - winLength; r++) {
            for (int c = 0; c <= size - winLength; c++) {
                boolean win = true;
                for (int k = 0; k < winLength; k++) {
                    if (!symbol.equals(board.getSymbolAt(r + k, c + k))) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        // Átlók (jobb felső → bal alsó)
        for (int r = 0; r <= size - winLength; r++) {
            for (int c = winLength - 1; c < size; c++) {
                boolean win = true;
                for (int k = 0; k < winLength; k++) {
                    if (!symbol.equals(board.getSymbolAt(r + k, c - k))) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        return false;
    }
}
