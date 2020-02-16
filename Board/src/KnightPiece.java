public class KnightPiece extends Pieces {
    public KnightPiece(Boolean isWhite, int r, int col) {
        super("Knight", isWhite, 4, r, col);
        moves = new BoardPosition[]{new BoardPosition(2,-1),new BoardPosition(1,-2),new BoardPosition(-1,-2),new BoardPosition(-2,-1),new BoardPosition(2,1),new BoardPosition(-1,2),new BoardPosition(1,2),new BoardPosition(2,1),};
    }

    // Needs to overwrite getPossiblePieces
}
