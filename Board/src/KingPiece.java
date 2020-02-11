import java.util.ArrayList;

public class KingPiece extends Pieces {
    public KingPiece(String t, String c, int r, int col){
        super(t, c, 1, r, col);
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
}
