import java.util.ArrayList;

public class Pieces {
    String type, color;
    // int score;
    String moves[];
    int[] positionOnBoard; // Possibly make a position class or get position from the board

    public Pieces(String t, String c){
        type = t;
        color = c;
    }

    public ArrayList<Board> getPossibleMoves(Board currentBoard){
        ArrayList<Board> boards = new ArrayList<Board>();
        // Add switch for each of the possible moves for the piece to generate all possible boards.
        return boards;
    }
}
