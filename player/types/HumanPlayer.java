package player.types;
import java.util.Scanner;

import game.Board;
import player.Player;

// human player na nag-iinput ng moves sa console
// extends Player class
public class HumanPlayer extends Player {
    private Scanner scanner;

    // constructor, set name at symbol ng player
    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
        this.scanner = new Scanner(System.in);
    }

    // override makeMove, humihingi ng move sa player via console
    @Override
    public int[] makeMove(Board board) {
        int row = -1;
        int col = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter row (1-3): ");
                row = scanner.nextInt() - 1; // convert sa 0-based index

                System.out.print("Enter column (1-3): ");
                col = scanner.nextInt() - 1; // convert sa 0-based index

                // check kung valid yung move
                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    if (board.isCellEmpty(row, col)) {
                        validInput = true;
                    } else {
                        System.out.println("❌ Cell already occupied! Try again.");
                    }
                } else {
                    System.out.println("❌ Invalid coordinates! Enter numbers between 1 and 3.");
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter numbers only.");
                scanner.nextLine(); // clear invalid input
            }
        }

        return new int[]{row, col};
    }
}
