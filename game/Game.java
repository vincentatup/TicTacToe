package game;
import java.util.Scanner;

import player.Player;


public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameRules rules;
    private boolean isGameActive;
    private Scanner scanner;


    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.rules = new TicTacToeRules();
        this.isGameActive = false;
        this.scanner = new Scanner(System.in);
    }


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


    private void playTurn() {
        System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
        
        // polymorphism in action - behavior depende sa player type
        int[] move = currentPlayer.makeMove(board);
        

        if (board.isCellEmpty(move[0], move[1])) {
            board.makeMove(move[0], move[1], currentPlayer.getSymbol());
        }
    }

    // switch to the other player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }


    private void announceWinner(char winnerSymbol) {
        Player winner = (player1.getSymbol() == winnerSymbol) ? player1 : player2;
        winner.incrementScore();
        
        System.out.println("\n========================================");
        System.out.println("🎉 GAME OVER! 🎉");
        System.out.println("========================================");
        System.out.println("Winner: " + winner.getName() + " (" + winner.getSymbol() + ")");
        System.out.println("========================================\n");
    }


    private void announceDraw() {
        System.out.println("\n========================================");
        System.out.println("🤝 GAME OVER! 🤝");
        System.out.println("========================================");
        System.out.println("It's a DRAW!");
        System.out.println("========================================\n");
    }


    private void displayScoreboard() {
        System.out.println("SCOREBOARD:");
        System.out.println(player1.getName() + ": " + player1.getScore() + " win(s)");
        System.out.println(player2.getName() + ": " + player2.getScore() + " win(s)");
        System.out.println();
    }


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


    private void displayFinalScores() {
        System.out.println("\nThanks for playing! Final Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore() + " win(s)");
        System.out.println(player2.getName() + ": " + player2.getScore() + " win(s)");
        System.out.println("\nGoodbye! 👋");
    }
}