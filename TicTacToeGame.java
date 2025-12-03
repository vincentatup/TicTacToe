import java.util.Scanner;

import game.Game;
import player.Player;
import player.types.ComputerPlayer;
import player.types.HumanPlayer;
import player.types.SmartComputerPlayer;

import java.util.InputMismatchException;

public class TicTacToeGame {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcome();
        
        boolean exitProgram = false;
        
        while (!exitProgram) {
            try {
                int choice = displayMenu();
                
                switch (choice) {
                    case 1:
                        startHumanVsHuman();
                        break;
                    case 2:
                        startHumanVsComputer();
                        break;
                    case 3:
                        startHumanVsSmartComputer();
                        break;
                    case 4:
                        System.out.println("\nThank you for playing! Goodbye! 👋");
                        exitProgram = true;
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please enter 1-4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    
    private static void displayWelcome() {
        System.out.println("========================================");
        System.out.println("     TIC-TAC-TOE GAME");
        System.out.println("========================================");
        System.out.println("Welcome! Let's play Tic-Tac-Toe!\n");
    }

    
    private static int displayMenu() {
        System.out.println("Select Game Mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer (Easy)");
        System.out.println("3. Human vs Smart Computer");
        System.out.println("4. Exit");
        System.out.print("\nEnter your choice: ");
        
        return scanner.nextInt();
    }

    // Human vs Human
    private static void startHumanVsHuman() {
        scanner.nextLine();
        
        System.out.println("\n--- Human vs Human Mode ---");
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();
        
        
        Player player1 = new HumanPlayer(player1Name, 'X');
        Player player2 = new HumanPlayer(player2Name, 'O');
        
        
        Game game = new Game(player1, player2);
        game.start();
    }

    // Human vs Computer
    private static void startHumanVsComputer() {
        scanner.nextLine();
        
        System.out.println("\n--- Human vs Computer Mode ---");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        
        
        Player player1 = new HumanPlayer(playerName, 'X');
        Player player2 = new ComputerPlayer("Computer", 'O');
        
        
        Game game = new Game(player1, player2);
        game.start();
    }

    // Human vs Smart Computer
    private static void startHumanVsSmartComputer() {
        scanner.nextLine();
        
        System.out.println("\n--- Human vs Smart Computer Mode ---");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        
        
        Player player1 = new HumanPlayer(playerName, 'X');
        Player player2 = new SmartComputerPlayer("Smart Bot", 'O');
        
        System.out.println("\n⚠️  Warning: The Smart Bot is unbeatable! Good luck! 🤖");
        
        
        Game game = new Game(player1, player2);
        game.start();
    }
}
