package pieces;

import board.BoardPosition;

public class KnightPiece extends Pieces {
    public KnightPiece(Boolean isWhite, int r, int col) {
        super("Knight", isWhite, 1, r, col);
        updateMovesAndAttack();
    }

    public void updateMovesAndAttack() {
        moves = new BoardPosition[]{new BoardPosition(2, -1), new BoardPosition(1, -2), new BoardPosition(-1, -2), new BoardPosition(-2, -1), new BoardPosition(2, 1), new BoardPosition(-1, 2), new BoardPosition(1, 2), new BoardPosition(2, 1),};
        attackablePositions = moves;

    }
}
