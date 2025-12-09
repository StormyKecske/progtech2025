package hu.milan.amoba;

public class Player {
    private final String name;
    private final String symbol;
    private int wins;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }
}
