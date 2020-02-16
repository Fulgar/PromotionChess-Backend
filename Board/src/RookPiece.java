import java.util.ArrayList;

public class RookPiece extends Pieces {
    public RookPiece(Boolean isWhite, int r, int col){
        super("Rook", isWhite, 7, r, col);
        moves = new String[]{"N", "S", "E", "W"};
    }
    public RookPiece(RookPiece rookPiece) {
        super(rookPiece.type, rookPiece.isWhite, rookPiece.speed, rookPiece.bp.getRow(), rookPiece.bp.getColumn());
        moves = new String[]{"N", "S", "E", "W"};
    }
}
