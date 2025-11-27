// rules ng Tic-Tac-Toe game
// dito nakalagay lahat ng logic kung paano mananalo o kung valid ang move
public class TicTacToeRules implements GameRules {

    // tinitingnan kung may panalo sa board
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

        // check main diagonal (kaliwa taas → kanan baba)
        if (board.getCell(0, 0) != ' ' &&
            board.getCell(0, 0) == board.getCell(1, 1) &&
            board.getCell(1, 1) == board.getCell(2, 2)) {
            return board.getCell(0, 0);
        }

        // check anti-diagonal (kanan taas → kaliwa baba)
        if (board.getCell(0, 2) != ' ' &&
            board.getCell(0, 2) == board.getCell(1, 1) &&
            board.getCell(1, 1) == board.getCell(2, 0)) {
            return board.getCell(0, 2);
        }

        return ' '; // walang panalo
    }

    // check kung draw (full board pero walang panalo)
    @Override
    public boolean isDraw(Board board) {
        return board.isFull() && checkWinner(board) == ' ';
    }

    // check kung valid ang move
    @Override
    public boolean isValidMove(Board board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board.isCellEmpty(row, col);
    }
}
