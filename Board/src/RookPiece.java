import java.util.ArrayList;

public class RookPiece extends Pieces {
    public RookPiece(String t, String c, int r, int col){
        super(t, c, 7, r, col);
        moves = new String[]{"N", "S", "E", "W"};
    }
}
