import java.util.Random;

// smart computer player na may strategy sa move
// extends Player class
public class SmartComputerPlayer extends Player {
    private Random random;

    // constructor, set name at symbol ng player
    public SmartComputerPlayer(String name, char symbol) {
        super(name, symbol);
        this.random = new Random();
    }

    // override makeMove, gamit ang strategic moves
    // strategy: 1. win kung puwede, 2. block opponent, 3. take center, 4. take corner, 5. random
    @Override
    public int[] makeMove(Board board) {
        System.out.println(getName() + " is thinking strategically...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int[] move;

        // try to win
        move = findWinningMove(board, getSymbol());
        if (move != null) {
            System.out.println(getName() + " is going for the win!");
            System.out.println(getName() + " chose: Row " + (move[0] + 1) + ", Column " + (move[1] + 1));
            return move;
        }

        // block opponent
        char opponentSymbol = (getSymbol() == 'X') ? 'O' : 'X';
        move = findWinningMove(board, opponentSymbol);
        if (move != null) {
            System.out.println(getName() + " is blocking opponent!");
            System.out.println(getName() + " chose: Row " + (move[0] + 1) + ", Column " + (move[1] + 1));
            return move;
        }

        // take center kung libre
        if (board.isCellEmpty(1, 1)) {
            System.out.println(getName() + " chose: Row 2, Column 2 (Center)");
            return new int[]{1, 1};
        }

        // take corner kung libre
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (board.isCellEmpty(corner[0], corner[1])) {
                System.out.println(getName() + " chose: Row " + (corner[0] + 1) + ", Column " + (corner[1] + 1));
                return corner;
            }
        }

        // take any available cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    System.out.println(getName() + " chose: Row " + (i + 1) + ", Column " + (j + 1));
                    return new int[]{i, j};
                }
            }
        }

        return null; // hindi dapat ma-reach
    }

    // hanapin kung may winning move para sa given symbol
    private int[] findWinningMove(Board board, char symbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    board.setCell(i, j, symbol);
                    boolean isWin = checkWinForSymbol(board, symbol);
                    board.setCell(i, j, ' '); // undo move
                    if (isWin) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    // check kung panalo na yung given symbol
    private boolean checkWinForSymbol(Board board, char symbol) {
        // check rows at columns
        for (int i = 0; i < 3; i++) {
            if ((board.getCell(i, 0) == symbol && board.getCell(i, 1) == symbol && board.getCell(i, 2) == symbol) ||
                (board.getCell(0, i) == symbol && board.getCell(1, i) == symbol && board.getCell(2, i) == symbol)) {
                return true;
            }
        }

        // check diagonals
        if ((board.getCell(0, 0) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 2) == symbol) ||
            (board.getCell(0, 2) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 0) == symbol)) {
            return true;
        }

        return false;
    }
}