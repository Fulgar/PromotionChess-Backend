import java.util.ArrayList;

public class Pieces {
    String type;
    Boolean isWhite;
    // int score;
    int speed;
    BoardPosition moves[];
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
        for (BoardPosition move: moves) {

            //is Index out of bounds
            if ((move.row + bp.row < 0 && move.row + bp.row > 7) || (move.column + bp.column < 0 && move.column + bp.column > 7))
                continue;

            //If there is no piece at that location
            if (currentBoard.board[move.row+ bp.row][move.column+bp.column] == null){
                boards.add(new Board(currentBoard.createFenString()));
                boards.get(boards.size() - 1).board[move.row + bp.row][move.column + bp.column] = boards.get(boards.size() - 1).board[bp.row][bp.column];
                boards.get(boards.size() - 1).board[bp.row][bp.column] = null;
                boards.get(boards.size() - 1).board[move.row+bp.row][move.column+bp.column].bp = new BoardPosition(move.row+bp.row,move.column+bp.column);
            //if there is a piece at the location but the piece is the same color
            }else if (currentBoard.board[move.row+ bp.row][move.column+bp.column].isWhite == this.isWhite)
                continue;
            else{//If the piece is an opposing player
                boards.add(new Board(currentBoard.createFenString()));
                boards.get(boards.size() - 1).capture(boards.get(boards.size() - 1).board[this.bp.row][this.bp.column], boards.get(boards.size() - 1).board[this.bp.row + move.row][this.bp.column + move.column] );
            }
        }
        return boards;
    }
}
