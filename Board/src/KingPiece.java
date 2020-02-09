import java.util.ArrayList;

public class KingPiece extends Pieces {

    public KingPiece(String t, String c){
        super(t, c, 1);
        moves = new String[]{"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
    }
}
