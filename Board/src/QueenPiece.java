import java.util.ArrayList;

public class QueenPiece extends Pieces {
    public QueenPiece(Boolean isWhite, int r, int col){
        super("Queen", isWhite, 7, r, col);
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
    public QueenPiece(QueenPiece queenPiece) {
        super(queenPiece.type, queenPiece.isWhite, queenPiece.speed, queenPiece.bp.getRow(), queenPiece.bp.getColumn());
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
}
