public class KnightPiece extends Pieces {
    public KnightPiece(Boolean isWhite, int r, int col) {
        super("Knight", isWhite, 4, r, col);
    }
    public KnightPiece(KnightPiece knightPiece) {super(knightPiece.type,knightPiece.isWhite,knightPiece.speed,knightPiece.bp.getRow(),knightPiece.bp.getColumn());}

    // Needs to overwrite getPossiblePieces
}
