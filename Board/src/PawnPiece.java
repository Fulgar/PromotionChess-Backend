public class PawnPiece extends Pieces {
    public PawnPiece(Boolean isWhite, int r, int col) {
        super("Pawn", isWhite, 1, r, col); // Change the move list based on color
        moves = new BoardPosition[]{new BoardPosition(-1,0), new BoardPosition(0,1), new BoardPosition(1,0), new BoardPosition(0,-1), new BoardPosition(-1,-1), new BoardPosition(-1,1), new BoardPosition(1,-1),new BoardPosition(1,1)};
    }


    // Need to overwrite getPossiblePieces
}
