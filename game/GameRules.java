package game;


public interface GameRules {


    char checkWinner(Board board);



    boolean isDraw(Board board);


    
    boolean isValidMove(Board board, int row, int col);
}
