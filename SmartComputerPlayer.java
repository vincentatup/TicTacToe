import java.util.Random;

/**
 * A smart computer player that tries to make the best moves in the game.
 * Uses some simple strategy to either win, block the opponent, or pick the best spot.
 */
public class SmartComputerPlayer extends Player {
    private Random random;

    public SmartComputerPlayer(String name, char symbol) {
        super(name, symbol);
        this.random = new Random();
    }

    /**
     * This is where the computer decides its move.
     * Strategy:
     * 1. Try to win if possible.
     * 2. Block the opponent from winning.
     * 3. Take the center if it's free.
     * 4. Take a corner if available.
     * 5. Pick any other open spot as a last resort.
     */
    @Override
    public int[] makeMove(Board board) {
        System.out.println(getName() + " is thinking strategically...");
        try {
            Thread.sleep(1000); // pause para kunyare nag-iisip
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int[] move;

        // Look for a winning move first
        move = findWinningMove(board, getSymbol());
        if (move != null) {
            System.out.println(getName() + " is going for the win!");
            System.out.println(getName() + " chose: Row " + (move[0] + 1) + ", Column " + (move[1] + 1));
            return move;
        }

        // If can't win, i-block yung kalaban
        char opponentSymbol = (getSymbol() == 'X') ? 'O' : 'X';
        move = findWinningMove(board, opponentSymbol);
        if (move != null) {
            System.out.println(getName() + " is blocking the opponent!");
            System.out.println(getName() + " chose: Row " + (move[0] + 1) + ", Column " + (move[1] + 1));
            return move;
        }

        // Take the center if it’s free
        if (board.isCellEmpty(1, 1)) {
            System.out.println(getName() + " chose: Row 2, Column 2 (Center)");
            return new int[]{1, 1};
        }

        // Otherwise, take any corner that's free
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (board.isCellEmpty(corner[0], corner[1])) {
                System.out.println(getName() + " chose: Row " + (corner[0] + 1) + ", Column " + (corner[1] + 1));
                return corner;
            }
        }

        // If nothing else, just pick the first empty spot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    System.out.println(getName() + " chose: Row " + (i + 1) + ", Column " + (j + 1));
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    /**
     * Looks for a move that would let 'symbol' win.
     * Returns the [row, col] if it exists, otherwise null.
     */
    private int[] findWinningMove(Board board, char symbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    // Temporarily make the move
                    board.makeMove(i, j, symbol);
                    
                    // See if this leads to a win
                    boolean isWin = checkWinForSymbol(board, symbol);
                    
                    // Undo the move
                    board.makeMove(i, j, ' ');
                    
                    if (isWin) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if the given symbol has a winning line on the board.
     */
    private boolean checkWinForSymbol(Board board, char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board.getCell(i, 0) == symbol && board.getCell(i, 1) == symbol && board.getCell(i, 2) == symbol) ||
                (board.getCell(0, i) == symbol && board.getCell(1, i) == symbol && board.getCell(2, i) == symbol)) {
                return true;
            }
        }

        // Check diagonals
        if ((board.getCell(0, 0) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 2) == symbol) ||
            (board.getCell(0, 2) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 0) == symbol)) {
            return true;
        }

        return false;
    }
}
