package game;
import java.util.Scanner;

import player.Player;

// main game class, nag-handle ng game flow
// uses Board, Player, and GameRules
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameRules rules;
    private boolean isGameActive;
    private Scanner scanner;

    // constructor, set up board at two players
    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Player 1 starts
        this.rules = new TicTacToeRules();
        this.isGameActive = false;
        this.scanner = new Scanner(System.in);
    }

    // start the game loop
    public void start() {
        boolean playAgain = true;

        while (playAgain) {
            playRound();
            displayScoreboard();
            playAgain = askPlayAgain();
            
            if (playAgain) {
                board.reset();
                currentPlayer = player1; // reset sa Player 1
            }
        }

        displayFinalScores();
    }

    // play one round
    private void playRound() {
        System.out.println("\n========================================");
        System.out.println("      NEW GAME STARTED!");
        System.out.println("========================================");
        System.out.println(player1 + " vs " + player2);
        
        board.reset();
        isGameActive = true;
        board.display();

        while (isGameActive) {
            playTurn();
            board.display();
            
            // check kung may nanalo
            char winner = rules.checkWinner(board);
            if (winner != ' ') {
                announceWinner(winner);
                isGameActive = false;
            } else if (rules.isDraw(board)) {
                announceDraw();
                isGameActive = false;
            } else {
                switchPlayer();
            }
        }
    }

    // play one turn
    private void playTurn() {
        System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
        
        // polymorphism in action - behavior depende sa player type
        int[] move = currentPlayer.makeMove(board);
        
        // gawin lang move kung empty yung cell
        if (board.isCellEmpty(move[0], move[1])) {
            board.makeMove(move[0], move[1], currentPlayer.getSymbol());
        }
    }

    // switch to the other player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // announce the winner
    private void announceWinner(char winnerSymbol) {
        Player winner = (player1.getSymbol() == winnerSymbol) ? player1 : player2;
        winner.incrementScore();
        
        System.out.println("\n========================================");
        System.out.println("🎉 GAME OVER! 🎉");
        System.out.println("========================================");
        System.out.println("Winner: " + winner.getName() + " (" + winner.getSymbol() + ")");
        System.out.println("========================================\n");
    }

    // announce draw
    private void announceDraw() {
        System.out.println("\n========================================");
        System.out.println("🤝 GAME OVER! 🤝");
        System.out.println("========================================");
        System.out.println("It's a DRAW!");
        System.out.println("========================================\n");
    }

    // show current scores
    private void displayScoreboard() {
        System.out.println("SCOREBOARD:");
        System.out.println(player1.getName() + ": " + player1.getScore() + " win(s)");
        System.out.println(player2.getName() + ": " + player2.getScore() + " win(s)");
        System.out.println();
    }

    // ask players kung gusto ulit maglaro
    private boolean askPlayAgain() {
        System.out.print("Play again? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        while (!response.equals("yes") && !response.equals("no") && 
               !response.equals("y") && !response.equals("n")) {
            System.out.print("Please enter 'yes' or 'no': ");
            response = scanner.nextLine().trim().toLowerCase();
        }
        
        return response.equals("yes") || response.equals("y");
    }

    // show final scores bago matapos
    private void displayFinalScores() {
        System.out.println("\nThanks for playing! Final Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore() + " win(s)");
        System.out.println(player2.getName() + ": " + player2.getScore() + " win(s)");
        System.out.println("\nGoodbye! 👋");
    }
}