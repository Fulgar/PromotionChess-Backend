public class PawnPiece extends Pieces {
    public PawnPiece(Boolean isWhite, int r, int col) {
        super("Pawn", isWhite, 1, r, col); // Change the move list based on color
    }


    // Need to overwrite getPossiblePieces
}
