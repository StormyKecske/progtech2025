package hu.milan.amoba;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:amoba.db";

    public Database() {
        try {
            // 🔑 JDBC driver kézi betöltése, ha nem Mavenes a projekt
            Class.forName("org.sqlite.JDBC");

            try (Connection conn = DriverManager.getConnection(URL)) {
                String sql = "CREATE TABLE IF NOT EXISTS players (" +
                        "name TEXT PRIMARY KEY, " +
                        "wins INTEGER DEFAULT 0)";
                conn.createStatement().execute(sql);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addWin(String name) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            // Ha nincs ilyen játékos, beszúrjuk
            String insert = "INSERT OR IGNORE INTO players(name, wins) VALUES(?, 0)";
            try (PreparedStatement psInsert = conn.prepareStatement(insert)) {
                psInsert.setString(1, name);
                psInsert.executeUpdate();
            }

            // Növeljük a győzelmek számát
            String update = "UPDATE players SET wins = wins + 1 WHERE name = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(update)) {
                psUpdate.setString(1, name);
                psUpdate.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printHighScores() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sql = "SELECT name, wins FROM players ORDER BY wins DESC";
            try (ResultSet rs = conn.createStatement().executeQuery(sql)) {
                System.out.println("\n=== High Score Táblázat ===");
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " - " + rs.getInt("wins") + " győzelem");
                }
                System.out.println("===========================\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
