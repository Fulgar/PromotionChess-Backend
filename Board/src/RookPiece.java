import java.util.ArrayList;

public class RookPiece extends Pieces {
    public RookPiece(Boolean isWhite, int r, int col){
        super("Rook", isWhite, 7, r, col);
        moves = new BoardPosition[]{new BoardPosition(-1,0), new BoardPosition(0,1), new BoardPosition(1,0), new BoardPosition(0,-1)};
    }

}
