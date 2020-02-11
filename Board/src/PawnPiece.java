public class PawnPiece extends Pieces {
    public PawnPiece(String t, String c, int s, int r, int col) {
        super(t, c, s, r, col); // Change the move list based on color
    }

    // Need to overwrite getPossiblePieces
}
