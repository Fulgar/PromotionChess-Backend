package pieces;

import board.BoardPosition;

public class PawnPiece extends Pieces {
    public PawnPiece(Boolean isWhite, int r, int col) {
        super("Pawn", isWhite, 1, r, col); // Change the move list based on color
        updateMovesAndAttack();
    }

    public void updateMovesAndAttack() {
        if (this.isWhite && this.bp.row == 6)
            moves = new BoardPosition[]{new BoardPosition(0, -1), new BoardPosition(0, 1), new BoardPosition(-1, -1), new BoardPosition(-1, 0), new BoardPosition(-1, 1), new BoardPosition(-2, 0)};
        else if (!this.isWhite && this.bp.row == 1)
            moves = new BoardPosition[]{new BoardPosition(0, -1), new BoardPosition(0, 1), new BoardPosition(1, -1), new BoardPosition(1, 0), new BoardPosition(1, 1), new BoardPosition(2, 0)};
        else if (this.isWhite)
            moves = new BoardPosition[]{new BoardPosition(0, -1), new BoardPosition(0, 1), new BoardPosition(-1, -1), new BoardPosition(-1, 0), new BoardPosition(-1, 1)};
        else
            moves = new BoardPosition[]{new BoardPosition(0, -1), new BoardPosition(0, 1), new BoardPosition(1, -1), new BoardPosition(1, 0), new BoardPosition(1, 1)};
        attackablePositions = new BoardPosition[]{new BoardPosition(-1, 0), new BoardPosition(0, 1), new BoardPosition(1, 0), new BoardPosition(0, -1), new BoardPosition(-1, -1), new BoardPosition(-1, 1), new BoardPosition(1, -1), new BoardPosition(1, 1)};
    }
}
