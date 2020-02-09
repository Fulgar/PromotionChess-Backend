public class Board {
    Pieces[][] board = new Pieces[8][8];
    int score;
    String fenString;

    public Board(String fen){ // Need to intake fenstring, Current color turn char, Bottom color char(Player Color), and Depth int
        // Create board from fenstring;

    }

    public boolean isPiece(){
        return true;
    }

    //public void Capture(){}

    public String createFenString(){
        String fen = "";
        return fen;
    }
}
