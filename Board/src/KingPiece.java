import java.util.ArrayList;

public class KingPiece extends Pieces {

    public KingPiece(String t, String c){
        super(t, c);
        moves = new String[8];
    }

    public ArrayList<Board> getPossibleMoves(Board currentBoard) {
        ArrayList<Board> boards = new ArrayList<Board>();
        // Add switch for each of the possible moves for the piece to generate all possible boards.
        return boards;
    }
}
