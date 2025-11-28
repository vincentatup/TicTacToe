package game;
// interface para sa game rules
// dito naka-define kung ano dapat gawin ng rules

public interface GameRules {

    // check kung may nanalo na sa board
    // return 'X' or 'O' kung may winner, ' ' kung wala
    char checkWinner(Board board);

    // check kung draw na yung game
    // true kung draw, false kung hindi
    boolean isDraw(Board board);

    // check kung valid yung move
    // true kung puwede, false kung hindi
    boolean isValidMove(Board board, int row, int col);
}
