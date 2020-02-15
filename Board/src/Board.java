public class Board {
    Pieces[][] board = new Pieces[8][8];
    int score;
    String fenString;

    public Board(String fen) { // Need to intake fenstring, Current color turn char, Bottom color char(Player Color), and Depth int
        // Create board from fenstring;
        int currentRow = 0;
        int currentColumn = 0;
        String[] reg = fen.split(" ");

        //ToDo reg[1] holds the value for who's move it is

        for (int i = 0; i < reg[0].length(); i++) {
            switch (reg[0].charAt(i)) {
                case 'P':
                    board[currentColumn][currentRow] = new PawnPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'p':
                    board[currentColumn][currentRow] = new PawnPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'R':
                    board[currentColumn][currentRow] = new RookPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'r':
                    board[currentColumn][currentRow] = new RookPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'N':
                    board[currentColumn][currentRow] = new KnightPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'n':
                    board[currentColumn][currentRow] = new KnightPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'B':
                    board[currentColumn][currentRow] = new BishopPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'b':
                    board[currentColumn][currentRow] = new BishopPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'Q':
                    board[currentColumn][currentRow] = new QueenPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'q':
                    board[currentColumn][currentRow] = new QueenPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'K':
                    board[currentColumn][currentRow] = new KingPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'k':
                    board[currentColumn][currentRow] = new KingPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case '/':
                    currentRow++;
                    currentColumn = 0;
                    break;
                default:
                {
                    String hold = "" + fen.charAt(i);
                    currentRow = currentRow + Integer.parseInt(hold);
                }
            }
        }
    }

    public Board(Board b, BoardPosition oldPos, BoardPosition newPos){ // Incoming board, old position and new position
        // Check if this is an attack and call capture?
    }



    public boolean isPiece(){
        return true;
    }

    private void Capture(){}

    //Lowercase is Black
    public String createFenString(){
        StringBuilder fen = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int count = 0;
            boolean isEmpty = false;
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == null){
                    isEmpty = true;
                    count++;
                    continue;
                }else if (isEmpty){
                    fen.append(count);
                    count = 0;
                }
                switch(board[j][i].type){
                    case "Pawn":
                        if (board[j][i].isWhite)
                            fen.append("P");
                        else
                            fen.append("p");
                        break;
                    case "Rook":
                        if (board[j][i].isWhite)
                            fen.append("R");
                        else
                            fen.append("r");
                        break;
                    case "Knight":
                        if (board[j][i].isWhite)
                            fen.append("N");
                        else
                            fen.append("n");
                        break;
                    case "Bishop":
                        if (board[j][i].isWhite)
                            fen.append("B");
                        else
                            fen.append("b");
                        break;
                    case "Queen":
                        if (board[j][i].isWhite)
                            fen.append("Q");
                        else
                            fen.append("q");
                        break;
                    case "King":
                        if (board[j][i].isWhite)
                            fen.append("K");
                        else
                            fen.append("k");
                        break;
                }
            }
            fen.append("/");
        }
        fen.append(" " + "w" + "");

        return fen.toString();
    }



}
