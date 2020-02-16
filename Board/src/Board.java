import score.ChessType;

import java.util.Hashtable;

public class Board {
    Pieces[][] board = new Pieces[8][8];
    int score;
    String fenString;
    boolean isWhiteMove;
    Hashtable<String,Number> captureOrder = new Hashtable<>();


    public Board(String fen) { // Need to intake fenstring, Current color turn char, Bottom color char(Player Color), and Depth int
        // Create board from fenstring;
        int currentRow = 0;
        int currentColumn = 0;
        String[] reg = fen.split(" ");
        captureOrder.put("Pawn", 1);
        captureOrder.put("Rook",2);
        captureOrder.put("Bishop",3);
        captureOrder.put("Knight",4);
        captureOrder.put("Queen",5);
        isWhiteMove = reg[1].equals("w");
        for (int i = 0; i < reg[0].length(); i++) {
            switch (reg[0].charAt(i)) {
                case 'P':
                    board[currentRow][currentColumn] = new PawnPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'p':
                    board[currentRow][currentColumn] = new PawnPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'R':
                    board[currentRow][currentColumn] = new RookPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'r':
                    board[currentRow][currentColumn] = new RookPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'N':
                    board[currentRow][currentColumn] = new KnightPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'n':
                    board[currentRow][currentColumn] = new KnightPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'B':
                    board[currentRow][currentColumn] = new BishopPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'b':
                    board[currentRow][currentColumn] = new BishopPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'Q':
                    board[currentRow][currentColumn] = new QueenPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'q':
                    board[currentRow][currentColumn] = new QueenPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'K':
                    board[currentRow][currentColumn] = new KingPiece(true, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case 'k':
                    board[currentRow][currentColumn] = new KingPiece(false, currentRow, currentColumn);
                    currentColumn++;
                    break;
                case '/':
                    currentRow++;
                    currentColumn = 0;
                    break;
                default:
                {
                    String hold = "" + fen.charAt(i);
                    currentColumn = currentColumn + Integer.parseInt(hold);
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

    private <CapturingPiece extends Pieces, CapturedPiece extends Pieces> void Capture(CapturingPiece capturingPiece, CapturedPiece capturedPiece){
        //ToDo Should we double check to make sure a piece isnt trying to take its own team's piece?
        //determines the next piece's order
        int newPieceOrder;
        if (captureOrder.get(capturedPiece.type).intValue() >= captureOrder.get(capturingPiece.type).intValue() + 3 )
            newPieceOrder = captureOrder.get(capturedPiece.type).intValue() - 1;
        else
            newPieceOrder = captureOrder.get(capturingPiece.type).intValue() + 1;
        //sets the captureing pieces old board position to null, and the new position is set the the captured pieces old position.
        switch (newPieceOrder){
            case 2:
                //its a rook
                board[capturedPiece.bp.row][capturedPiece.bp.column] = new RookPiece(capturingPiece.isWhite,capturedPiece.bp.row,capturedPiece.bp.getColumn());
                board[capturingPiece.bp.row][capturingPiece.bp.column] = null;
                break;
            case 3:
                //its a Bishop
                board[capturedPiece.bp.row][capturedPiece.bp.column] = new BishopPiece(capturingPiece.isWhite,capturedPiece.bp.row,capturedPiece.bp.getColumn());
                board[capturingPiece.bp.row][capturingPiece.bp.column] = null;
                break;
            case 4:
                //it's a knight
                board[capturedPiece.bp.row][capturedPiece.bp.column] = new KnightPiece(capturingPiece.isWhite,capturedPiece.bp.row,capturedPiece.bp.getColumn());
                board[capturingPiece.bp.row][capturingPiece.bp.column] = null;
                break;
            case 5:
                //its a Queen
                board[capturedPiece.bp.row][capturedPiece.bp.column] = new QueenPiece(capturingPiece.isWhite,capturedPiece.bp.row,capturedPiece.bp.getColumn());
                board[capturingPiece.bp.row][capturingPiece.bp.column] = null;
                break;
            case 1:
                //It's a pawn
                board[capturedPiece.bp.row][capturedPiece.bp.column] = new PawnPiece(capturingPiece.isWhite,capturedPiece.bp.row,capturedPiece.bp.getColumn());
                board[capturingPiece.bp.row][capturingPiece.bp.column] = null;
                break;
        }

    }

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
