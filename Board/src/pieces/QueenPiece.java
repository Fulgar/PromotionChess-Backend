package pieces;

import board.BoardPosition;

public class QueenPiece extends Pieces {
    public QueenPiece(Boolean isWhite, int r, int col) {
        super("Queen", isWhite, 7, r, col);
        updateMovesAndAttack();
    }

    public void updateMovesAndAttack() {
        moves = new BoardPosition[]{new BoardPosition(-1, 0), new BoardPosition(0, 1), new BoardPosition(1, 0), new BoardPosition(0, -1), new BoardPosition(-1, -1), new BoardPosition(-1, 1), new BoardPosition(1, -1), new BoardPosition(1, 1)};
        attackablePositions = moves;
    }
}
