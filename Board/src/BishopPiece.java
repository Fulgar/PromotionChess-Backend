import java.util.ArrayList;

public class BishopPiece extends Pieces {
    public BishopPiece(String t, String c, int r, int col){
        super(t, c, 7, r, col);
        moves = new String[]{"NE", "NW", "SE", "SW"};
    }
}
