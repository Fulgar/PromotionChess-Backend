package pieces;

import board.Board;
import board.BoardPosition;

import java.util.ArrayList;

public class Pieces {
    String type;
    Boolean isWhite;
    int speed;
    BoardPosition[] moves;
    BoardPosition[] attackablePositions;
    BoardPosition bp;

    public Pieces(String t, Boolean isWhite, int s, int r, int col) {
        type = t;
        this.isWhite = isWhite;
        speed = s;
        bp = new BoardPosition(r, col);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsWhite() {
        return isWhite;
    }

    public void setWhite(Boolean white) {
        isWhite = white;
    }

    public BoardPosition[] getMoves() {
        return moves;
    }

    public BoardPosition[] getAttackablePositions() {
        return attackablePositions;
    }

    public BoardPosition getBp() {
        return bp;
    }

    public void setBp(BoardPosition bp) {
        this.bp = bp;
    }

    public ReturnGetPossibleMoves getPossibleMoves(Board currentBoard) {
        ReturnGetPossibleMoves returnGetPossibleMoves = new ReturnGetPossibleMoves();
        ArrayList<Board> boards = new ArrayList<Board>();
        BoardPosition tempBP = new BoardPosition(0, 0);
        // This will generate all possible boards and add them to the ArrayList to be returned.
        if (moves == attackablePositions) {
            for (BoardPosition move : moves) {

                //is Index out of bounds
                for (int i = 1; i <= this.speed; i++) {
                    if ((((move.row * i) + bp.row) >= 0 && ((move.row * i) + bp.row) <= 7) && (((move.column * i) + bp.column) >= 0 && ((move.column * i) + bp.column) <= 7)) {
                        //If there is no piece at that location
                        if (currentBoard.board[(move.row * i) + bp.row][(move.column * i) + bp.column] == null) {
                            Board newBoard = new Board(currentBoard.createFenString(), currentBoard.getScore());
                            newBoard.updateFenField();
                            boards.add(newBoard);
                            boards.get(boards.size() - 1).board[(move.row * i) + bp.row][(move.column * i) + bp.column] = boards.get(boards.size() - 1).board[bp.row][bp.column];
                            boards.get(boards.size() - 1).board[bp.row][bp.column] = null;
                            boards.get(boards.size() - 1).board[(move.row * i) + bp.row][(move.column * i) + bp.column].bp = new BoardPosition((move.row * i) + bp.row, (move.column * i) + bp.column);
                            boards.get(boards.size() - 1).updateFenField();
                            //if there is a piece at the location but the piece is the same color
                        } else if (currentBoard.board[(move.row * i) + bp.row][(move.column * i) + bp.column].isWhite == this.isWhite)
                            break;
                        else {//If the piece is an opposing player
                            Board newBoard = new Board(currentBoard.createFenString(), currentBoard.getScore());
                            newBoard.updateFenField();
                            boards.add(newBoard);
                            boards.get(boards.size() - 1).capture(boards.get(boards.size() - 1).board[this.bp.row][this.bp.column], boards.get(boards.size() - 1).board[this.bp.row + (move.row * i)][this.bp.column + (move.column * i)]);
                            boards.get(boards.size() - 1).updateFenField();
                            returnGetPossibleMoves.setAttacking(true);
                            break;
                        }
                    }
                }
            }
            returnGetPossibleMoves.setPossibleMoves(boards);
            return returnGetPossibleMoves;
        } else {//This move piece has cancer and needs to be handled in a special way.
            for (BoardPosition move : moves) {
                for (int i = 1; i <= this.speed; i++) {
                    if ((((move.row * i) + bp.row) >= 0 && ((move.row * i) + bp.row) <= 7) && (((move.column * i) + bp.column) >= 0 && ((move.column * i) + bp.column) <= 7)) {
                        if (currentBoard.board[(move.row * i) + bp.row][(move.column * i) + bp.column] == null) {
                            Board newBoard = new Board(currentBoard.createFenString(), currentBoard.getScore());
                            newBoard.updateFenField();
                            boards.add(newBoard);
                            boards.get(boards.size() - 1).board[(move.row * i) + bp.row][(move.column * i) + bp.column] = boards.get(boards.size() - 1).board[bp.row][bp.column];
                            boards.get(boards.size() - 1).board[bp.row][bp.column] = null;
                            boards.get(boards.size() - 1).board[(move.row * i) + bp.row][(move.column * i) + bp.column].bp = new BoardPosition((move.row * i) + bp.row, (move.column * i) + bp.column);
                            boards.get(boards.size() - 1).updateFenField();
                        }
                    }
                }
                for (BoardPosition attack : attackablePositions) {
                    for (int i = 1; i <= this.speed; i++) {
                        if (((attack.row + bp.row) >= 0 && (attack.row + bp.row) <= 7) && ((attack.column + bp.column) >= 0 && (attack.column + bp.column) <= 7)) {
                            if ((currentBoard.board[attack.row + bp.row][attack.column + bp.column] != null) && (currentBoard.board[attack.row + bp.row][attack.column + bp.column].isWhite != isWhite)) {
                                Board newBoard = new Board(currentBoard.createFenString(), currentBoard.getScore());
                                boards.add(newBoard);
                                boards.get(boards.size() - 1).capture(boards.get(boards.size() - 1).board[this.bp.row][this.bp.column], boards.get(boards.size() - 1).board[this.bp.row + attack.row][this.bp.column + attack.column]);
                                boards.get(boards.size() - 1).updateFenField();
                                returnGetPossibleMoves.setAttacking(true);
                                break;
                            }
                        }
                    }

                }
            }
            returnGetPossibleMoves.setPossibleMoves(boards);
            return returnGetPossibleMoves;
        }

    }
}
