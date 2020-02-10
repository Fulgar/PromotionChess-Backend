import java.util.ArrayList;

public class QueenPiece extends Pieces {
    public QueenPiece(String t, String c){
        super(t, c, 8);
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
}
