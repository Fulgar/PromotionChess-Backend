package board;

import pieces.*;
import score.ChessType;
import score.Score;

import java.util.Hashtable;

public class Board {
    public Pieces[][] board = new Pieces[8][8];
    Score score;
    private String fenString;
    boolean isWhiteMove;
    Hashtable<String, Integer> captureOrder = new Hashtable<>();

    public Board() {

    }
    public Board(String fen, Score score) { // Need to intake fenstring, Current color turn char, Bottom color char(Player Color), and Depth int
        // Create board from fenstring;

        int currentRow = 0;
        int currentColumn = 0;
        String[] reg = fen.split(" ");
        captureOrder.put("Pawn", 1);
        captureOrder.put("Rook", 2);
        captureOrder.put("Bishop", 3);
        captureOrder.put("Knight", 4);
        captureOrder.put("Queen", 5);
        captureOrder.put("King", 6);
        //isWhiteMove = reg[1].equals("w");
        for (int i = 0; i < reg[0].length(); i++) {
            switch (reg[0].charAt(i)) {
                case 'P':
                    if(currentRow == 0){
                        board[currentRow][currentColumn] = new QueenPiece(true, currentRow, currentColumn);
                    }
                    else {
                        board[currentRow][currentColumn] = new PawnPiece(true, currentRow, currentColumn);
                    }
                    currentColumn++;
                    break;
                case 'p':
                    if(currentRow == 7){
                        board[currentRow][currentColumn] = new QueenPiece(false, currentRow, currentColumn);
                    }
                    else {
                        board[currentRow][currentColumn] = new PawnPiece(false, currentRow, currentColumn);
                    }
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
                default: {
                    String hold = "" + fen.charAt(i);
                    currentColumn = currentColumn + Integer.parseInt(hold);
                }
            }
        }
        if (score == null)
            this.score = new Score(this);
        else
            this.score = new Score(score);

        this.fenString = this.createFenString();
    }
    public Board(Board b, BoardPosition oldPos, BoardPosition newPos) { // Incoming board, old position and new position
        // Check if this is an attack and call capture?
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getAIScore() {
        return score.getBlackScore() - score.getWhiteScore();
    }

    public boolean isPiece() {
        return true;
    }

    public <CapturingPiece extends Pieces, CapturedPiece extends Pieces> void capture(CapturingPiece capturingPiece, CapturedPiece capturedPiece) {
        //ToDo Should we double check to make sure a piece isnt trying to take its own team's piece?
        //determines the next piece's order
        if (capturingPiece.getIsWhite()){
            score.setWhiteScore(score.getWhiteScore() - ChessType.valueOf(capturingPiece.getType().toUpperCase()).getValue());
            score.setBlackScore(score.getBlackScore() - ChessType.valueOf(capturedPiece.getType().toUpperCase()).getValue());
        } else{
            score.setWhiteScore(score.getWhiteScore() - ChessType.valueOf(capturedPiece.getType().toUpperCase()).getValue());
            score.setBlackScore(score.getBlackScore() - ChessType.valueOf(capturingPiece.getType().toUpperCase()).getValue());
        }

        int newPieceOrder;

        if (capturedPiece.getType().equals("King")) {
            //GAME OVER
            newPieceOrder = 7;
            // } else if (captureOrder.get(capturedPiece.getType()).intValue() >= captureOrder.get(capturingPiece.getType()).intValue() + 3)
        } else if (captureOrder.get(capturedPiece.getType()) >= captureOrder.get(capturingPiece.getType()) + 3)
            newPieceOrder = captureOrder.get(capturedPiece.getType()) - 1;
        else if (!capturingPiece.getType().equals("Queen"))
            newPieceOrder = captureOrder.get(capturingPiece.getType()) + 1;
        else
            newPieceOrder = captureOrder.get(capturingPiece.getType());
        //sets the captureing pieces old board position to null, and the new position is set the the captured pieces old position.
        switch (newPieceOrder) {
            case 2:
                //its a rook
                board[capturedPiece.getBp().row][capturedPiece.getBp().column] = new RookPiece(capturingPiece.getIsWhite(), capturedPiece.getBp().row, capturedPiece.getBp().getColumn());
                board[capturingPiece.getBp().row][capturingPiece.getBp().column] = null;
                if (capturingPiece.getIsWhite())
                    score.setWhiteScore(score.getWhiteScore() + ChessType.ROOK.getValue());
                else
                    score.setBlackScore(score.getBlackScore() + ChessType.ROOK.getValue());

                break;
            case 3:
                //its a Bishop
                board[capturedPiece.getBp().row][capturedPiece.getBp().column] = new BishopPiece(capturingPiece.getIsWhite(), capturedPiece.getBp().row, capturedPiece.getBp().getColumn());
                board[capturingPiece.getBp().row][capturingPiece.getBp().column] = null;
                if (capturingPiece.getIsWhite())
                    score.setWhiteScore(score.getWhiteScore() + ChessType.BISHOP.getValue());
                else
                    score.setBlackScore(score.getBlackScore() + ChessType.BISHOP.getValue());
                break;
            case 4:
                //it's a knight
                board[capturedPiece.getBp().row][capturedPiece.getBp().column] = new KnightPiece(capturingPiece.getIsWhite(), capturedPiece.getBp().row, capturedPiece.getBp().getColumn());
                board[capturingPiece.getBp().row][capturingPiece.getBp().column] = null;
                if (capturingPiece.getIsWhite())
                    score.setWhiteScore(score.getWhiteScore() + ChessType.KNIGHT.getValue());
                else
                    score.setBlackScore(score.getBlackScore() + ChessType.KNIGHT.getValue());
                break;
            case 5:
                //its a Queen
                board[capturedPiece.getBp().row][capturedPiece.getBp().column] = new QueenPiece(capturingPiece.getIsWhite(), capturedPiece.getBp().row, capturedPiece.getBp().getColumn());
                board[capturingPiece.getBp().row][capturingPiece.getBp().column] = null;
                if (capturingPiece.getIsWhite())
                    score.setWhiteScore(score.getWhiteScore() + ChessType.QUEEN.getValue());
                else
                    score.setBlackScore(score.getBlackScore() + ChessType.QUEEN.getValue());
                break;
            case 1:
                //It's a pawn
                board[capturedPiece.getBp().row][capturedPiece.getBp().column] = new PawnPiece(capturingPiece.getIsWhite(), capturedPiece.getBp().row, capturedPiece.getBp().getColumn());
                board[capturingPiece.getBp().row][capturingPiece.getBp().column] = null;
                if (capturingPiece.getIsWhite())
                    score.setWhiteScore(score.getWhiteScore() + ChessType.PAWN.getValue());
                else
                    score.setBlackScore(score.getBlackScore() + ChessType.PAWN.getValue());
                break;
            case 7:
                //It's a king
                board[capturedPiece.getBp().row][capturedPiece.getBp().column] = new QueenPiece(capturingPiece.getIsWhite(), capturedPiece.getBp().row, capturedPiece.getBp().getColumn());
                board[capturingPiece.getBp().row][capturingPiece.getBp().column] = null;
                if (capturingPiece.getIsWhite())
                    score.setWhiteScore(score.getWhiteScore() + ChessType.KING.getValue());
                else
                    score.setBlackScore(score.getBlackScore() + ChessType.KING.getValue());
                break;

            default:
                break;
        }

    }

    public void updateFenField()
    {
        this.fenString = this.createFenString();
    }

    //Lowercase is Black
    public String createFenString() {
        StringBuilder fen = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int count = 0;
            boolean isEmpty = false;
            for (int j = 0; j < 8; j++) {
            	if(board[i][j] == null && j ==7) {
            		fen.append(count + 1);
            		break;
            	}
            	else if (board[i][j] == null) {
                    isEmpty = true;
                    count++;
                    continue;
                } else if (isEmpty && count != 0) {
                    fen.append(count);
                    count = 0;
                }
                switch (board[i][j].getType()) {
                    case "Pawn":
                        if (board[i][j].getIsWhite())
                            fen.append("P");
                        else
                            fen.append("p");
                        break;
                    case "Rook":
                        if (board[i][j].getIsWhite())
                            fen.append("R");
                        else
                            fen.append("r");
                        break;
                    case "Knight":
                        if (board[i][j].getIsWhite())
                            fen.append("N");
                        else
                            fen.append("n");
                        break;
                    case "Bishop":
                        if (board[i][j].getIsWhite())
                            fen.append("B");
                        else
                            fen.append("b");
                        break;
                    case "Queen":
                        if (board[i][j].getIsWhite())
                            fen.append("Q");
                        else
                            fen.append("q");
                        break;
                    case "King":
                        if (board[i][j].getIsWhite())
                            fen.append("K");
                        else
                            fen.append("k");
                        break;
                }
            }
            if (i != 7)
            {
                fen.append("/");
            }

        }


        return fen.toString();
    }

    public void printBoard() {
        for (Pieces[] row :
                board) {
            for (Pieces piece :
                    row) {
                if (piece == null)
                    System.out.print(" \t");
                else {
                    if (piece.getIsWhite())
                        System.out.print(piece.getType().toUpperCase().charAt(0) + "\t");
                    else
                        System.out.print(piece.getType().toLowerCase().charAt(0) + "\t");
                }
            }
            System.out.println("");
        }
        System.out.println("_____________________________________________");
    }

    public void copyObjectKeepReference(Board copyFrom){
        this.score = copyFrom.score;
        this.board = copyFrom.board;
        this.isWhiteMove = copyFrom.isWhiteMove;
        this.fenString = copyFrom.fenString;
        this.captureOrder = copyFrom.captureOrder;
    }

    @Override
    public String toString()
    {
        return "Board{" +
                "fenString='" + fenString + '\'' +
                '}';
    }
}
