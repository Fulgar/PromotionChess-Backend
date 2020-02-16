import java.util.ArrayList;

public class KingPiece extends Pieces {
    public KingPiece(Boolean isWhite, int r, int col){
        super("King", isWhite, 1, r, col);
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
    public KingPiece(KingPiece kingPiece) {super(kingPiece.type,kingPiece.isWhite,kingPiece.speed,kingPiece.bp.getRow(),kingPiece.bp.getColumn());
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
}
