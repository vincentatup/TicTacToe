package player;


import game.Board;

public abstract class Player {
    private String name;
    private char symbol;
    private int score;

    // constructor, set name at symbol
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    // getters
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }


    public void incrementScore() {
        this.score++;
    }

    // abstract
    public abstract int[] makeMove(Board board);


    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
