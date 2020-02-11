public class Board {
    Pieces[][] board = new Pieces[8][8];
    int score;
    String fenString;

    public Board(String fen){ // Need to intake fenstring, Current color turn char, Bottom color char(Player Color), and Depth int
        // Create board from fenstring;

    }

    public Board(Board b, BoardPosition oldPos, BoardPosition newPos){ // Incoming board, old position and new position
        // Check if this is an attack and call capture?
    }

    public boolean isPiece(){
        return true;
    }

    private void Capture(){}

    public String createFenString(){
        String fen = "";
        return fen;
    }
}
