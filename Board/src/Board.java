public class Board {
    Pieces[][] board = new Pieces[8][8];
    int score;
    String fenString;

    public Board(String fen){
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
