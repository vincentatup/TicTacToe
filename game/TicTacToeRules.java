package game;


public class TicTacToeRules implements GameRules {

//checking winner
    @Override
    public char checkWinner(Board board) {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) != ' ' &&
                board.getCell(i, 0) == board.getCell(i, 1) &&
                board.getCell(i, 1) == board.getCell(i, 2)) {
                return board.getCell(i, 0);
            }
        }

        // check columns
        for (int j = 0; j < 3; j++) {
            if (board.getCell(0, j) != ' ' &&
                board.getCell(0, j) == board.getCell(1, j) &&
                board.getCell(1, j) == board.getCell(2, j)) {
                return board.getCell(0, j);
            }
        }

        // check diagonal
        if (board.getCell(0, 0) != ' ' &&
            board.getCell(0, 0) == board.getCell(1, 1) &&
            board.getCell(1, 1) == board.getCell(2, 2)) {
            return board.getCell(0, 0);
        }


        if (board.getCell(0, 2) != ' ' &&
            board.getCell(0, 2) == board.getCell(1, 1) &&
            board.getCell(1, 1) == board.getCell(2, 0)) {
            return board.getCell(0, 2);
        }

        return ' ';
    }


    @Override
    public boolean isDraw(Board board) {
        return board.isFull() && checkWinner(board) == ' ';
    }


    @Override
    public boolean isValidMove(Board board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board.isCellEmpty(row, col);
    }
}
