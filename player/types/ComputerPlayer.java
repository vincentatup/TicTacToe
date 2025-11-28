package player.types;
import java.util.Random;

import game.Board;
import player.Player;

// computer player na random lang mag-move
// extends Player class
public class ComputerPlayer extends Player {
    private Random random;

    // constructor, set name at symbol ng player
    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
        this.random = new Random();
    }

    // override makeMove, pipili ng random na empty spot sa board
    @Override
    public int[] makeMove(Board board) {
        int row, col;
        
        // repeat until makahanap ng empty cell
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.isCellEmpty(row, col));

        System.out.println(getName() + " is thinking...");
        try {
            Thread.sleep(1000); // konting pause para mukhang nag-iisip
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(getName() + " chose: Row " + (row + 1) + ", Column " + (col + 1));
        return new int[]{row, col};
    }
}
