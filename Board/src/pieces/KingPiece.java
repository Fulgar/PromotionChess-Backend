package pieces;

import board.BoardPosition;

public class KingPiece extends Pieces {
    public KingPiece(Boolean isWhite, int r, int col) {
        super("King", isWhite, 1, r, col);
        updateMovesAndAttack();
    }

    public void updateMovesAndAttack() {
        moves = new BoardPosition[]{new BoardPosition(-1, 0), new BoardPosition(0, 1), new BoardPosition(1, 0), new BoardPosition(0, -1)};
        attackablePositions = moves;
    }
}
