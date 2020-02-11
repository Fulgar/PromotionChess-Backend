import java.util.ArrayList;

public class QueenPiece extends Pieces {
    public QueenPiece(String t, String c, int r, int col){
        super(t, c, 8, r, col);
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
}
