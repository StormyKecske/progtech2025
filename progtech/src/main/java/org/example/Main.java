package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Database db = new Database();

        while (true) {
            System.out.println("\n=== AMŐBA JÁTÉK ===");
            System.out.println("1. Új játék indítása");
            System.out.println("2. High score megtekintése");
            System.out.println("3. Kilépés");
            System.out.print("Válassz: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Game game = new Game(db);
                    game.start();
                    break;

                case 2:
                    printHighScores(db);
                    break;

                case 3:
                    System.out.println("Kilépés...");
                    return;

                default:
                    System.out.println("Érvénytelen választás.");
            }
        }
    }

    private static void printHighScores(Database db) {
        List<Player> scores = db.getHighScores();

        System.out.println("\n=== HIGH SCORES ===");
        System.out.printf("%-20s %s%n", "Játékos", "Győzelmek");
        System.out.println("-------------------------------");

        for (Player p : scores) {
            System.out.printf("%-20s %d%n", p.getName(), p.getWins());
        }
    }
}
