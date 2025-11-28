package game;
// Board class for Tic-Tac-Toe
// handles the game grid
public class Board {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = ' ';
    private char[][] grid;

    // constructor, sets up an empty board
    public Board() {
        grid = new char[BOARD_SIZE][BOARD_SIZE];
        reset();
    }

    // clear the board
    public void reset() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                grid[i][j] = EMPTY_CELL;
            }
        }
    }

    // try to make a move, returns false if spot is invalid
    public boolean makeMove(int row, int col, char symbol) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    // check if a spot is empty
    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == EMPTY_CELL;
    }

    // check if the board is full
    public boolean isFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (grid[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    // get what symbol is at a spot
    public char getCell(int row, int col) {
        return grid[row][col];
    }

    // set a spot to a symbol (useful for testing or AI moves)
    public void setCell(int row, int col, char symbol) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            grid[row][col] = symbol;
        }
    }

    // print the board to the console
    public void display() {
        System.out.println("\nCurrent Board:");
        System.out.println("  1   2   3");
        
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(grid[i][j]);
                if (j < BOARD_SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            
            if (i < BOARD_SIZE - 1) {
                System.out.println("  -----------");
            }
        }
        System.out.println();
    }
}