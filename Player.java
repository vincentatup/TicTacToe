// abstract class para sa player
// dito naka-hold yung name, symbol, at score
public abstract class Player {
    private String name;
    private char symbol;
    private int score;

    // constructor, set name at symbol, score starts at 0
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    // getters para makuha yung info ng player
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    // increase score ng player
    public void incrementScore() {
        this.score++;
    }

    // abstract method, bawat player type may sariling move logic
    public abstract int[] makeMove(Board board);

    // para sa printing ng player info
    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
