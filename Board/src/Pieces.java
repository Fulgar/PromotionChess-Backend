import java.util.ArrayList;

public abstract class Pieces {
    String type, color;
    // int score;
    String moves[];
    int[] positionOnBoard; // Possibly make a position class or get position from the board

    public Pieces(String t, String c){
        type = t;
        color = c;
    }

    public abstract ArrayList<Board> getPossibleMoves(Board currentBoard);
}
