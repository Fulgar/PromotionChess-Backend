import java.util.ArrayList;

public class Pieces {
    String type, color;
    // int score;
    int speed;
    String moves[];
    BoardPosition bp;

    public Pieces(String t, String c, int s, int r, int col){
        type = t;
        color = c;
        speed = s;
        bp = new BoardPosition(r, col);
    }

    public ArrayList<Board> getPossibleMoves(Board currentBoard){
        ArrayList<Board> boards = new ArrayList<Board>();
        // This will generate all possible boards and add them to the ArrayList to be returned.
        for (String move: moves) {
            switch (move){
                case "N": break;
                case "S": break;
                case "E": break;
                case "W": break;
                case "NE": break;
                case "NW": break;
                case "SE": break;
                case "SW": break;
                default:
                    throw new IllegalStateException("Unexpected value: " + move);
            }
        }
        return boards;
    }
}
