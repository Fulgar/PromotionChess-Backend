package pieces;


import board.BoardPosition;

public class BishopPiece extends Pieces {
    public BishopPiece(Boolean isWhite, int r, int col) {
        super("Bishop", isWhite, 7, r, col);
        updateMovesAndAttack();
    }

    public void updateMovesAndAttack() {
        moves = new BoardPosition[]{new BoardPosition(-1, -1), new BoardPosition(-1, 1), new BoardPosition(1, -1), new BoardPosition(1, 1)};
        attackablePositions = moves;
    }
}
