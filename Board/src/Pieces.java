import java.util.ArrayList;

public class Pieces {
    String type;
    Boolean isWhite;
    // int score;
    int speed;
    String moves[];
    BoardPosition bp;

    public Pieces(String t, Boolean isWhite, int s, int r, int col){
        type = t;
        this.isWhite = isWhite;
        speed = s;
        bp = new BoardPosition(r, col);
    }



    public ArrayList<Board> getPossibleMoves(Board currentBoard){
        ArrayList<Board> boards = new ArrayList<Board>();
        BoardPosition tempBP = new BoardPosition(0, 0);
        // This will generate all possible boards and add them to the ArrayList to be returned.
        for (String move: moves) {
            switch (move){
                case "Pawn":
                    for(int i = 0; i < bp.getRow(); i++){
                        // Generate all boards north of position


                    }
                    break;
                case "S":
                    for(int i = bp.getRow(); i < 7; i++){
                        // Generate all boards south of position
                    }
                    break;
                case "E":
                    for(int i = bp.getColumn(); i < 7; i++){
                        // East positions
                    }
                    break;
                case "W":
                    for(int i = 0; i < bp.getColumn(); i++){
                        // West positions
                    }
                    break;
                case "NE":
                    tempBP = this.bp;
                    while(tempBP.getRow() != 0 || tempBP.getColumn() != 7){
                        // North East positions
                    }
                    break;
                case "NW":
                    tempBP = this.bp;
                    while(tempBP.getRow() != 0 || tempBP.getColumn() != 0){
                        // North West positions
                    }
                    break;
                case "SE":
                    tempBP = this.bp;
                    while(tempBP.getRow() != 7 || tempBP.getColumn() != 7){
                        // South East positions
                    }
                    break;
                case "SW":
                    tempBP = this.bp;
                    while(tempBP.getRow() != 7 || tempBP.getColumn() != 0){
                        // South West positions
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + move);
            }
        }
        return boards;
    }
}
