import java.util.ArrayList;

public class BishopPiece extends Pieces {
    public BishopPiece(Boolean isWhite, int r, int col) {
        super("Bishop", isWhite, 7, r, col);
        moves = new String[]{"NE", "NW", "SE", "SW"};
    }

    public BishopPiece(BishopPiece bishopPiece) {
        super(bishopPiece.type, bishopPiece.isWhite, bishopPiece.speed, bishopPiece.bp.getRow(), bishopPiece.bp.getColumn());
        moves = new String[]{"NE", "NW", "SE", "SW"};

    }
}
