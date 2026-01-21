package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:amoba.db";

    public Database() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String createTable = "CREATE TABLE IF NOT EXISTS players (" +
                    "name TEXT PRIMARY KEY," +
                    "wins INTEGER NOT NULL" +
                    ");";

            stmt.execute(createTable);

        } catch (SQLException e) {
            System.out.println("Hiba az adatbázis inicializálásakor: " + e.getMessage());
        }
    }

    public void addWin(String name) {
        String query =
                "INSERT INTO players (name, wins) VALUES (?, 1) " +
                        "ON CONFLICT(name) DO UPDATE SET wins = wins + 1;";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Hiba a győzelem növelésekor: " + e.getMessage());
        }
    }

    public int getWins(String name) {
        String query = "SELECT wins FROM players WHERE name = ?;";
        int wins = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                wins = rs.getInt("wins");
            }

        } catch (SQLException e) {
            System.out.println("Hiba a győzelmek lekérdezésekor: " + e.getMessage());
        }

        return wins;
    }

    public List<Player> getHighScores() {
        List<Player> scores = new ArrayList<>();
        String query = "SELECT name, wins FROM players ORDER BY wins DESC;";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                scores.add(new Player(
                        rs.getString("name"),
                        rs.getInt("wins")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Hiba a high scores lekérdezésekor: " + e.getMessage());
        }

        return scores;
    }
}
