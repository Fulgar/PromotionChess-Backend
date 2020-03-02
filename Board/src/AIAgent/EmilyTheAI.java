package AIAgent;


import board.Board;
import pieces.Pieces;

import java.util.ArrayList;

public class EmilyTheAI {
    private int searthDepth = 4;
    private String currentBestMove = null;


    public EmilyTheAI(){}


    public Board  returnBestMove(Board board, Board alpha, Board beta, boolean isMaximizingPlayer, int searthDepth, boolean isStartingPlayerWhite){
        Board currentBestMove;
        int bestValue;
        if (searthDepth == 0){
            return board;
        }

        if (isMaximizingPlayer){
            bestValue = Integer.MIN_VALUE;
            for (Pieces[] row:
                    board.board) {
                for (Pieces piece:
                        row) {
                        for (Board currentPossibleMove:
                             piece.getPossibleMoves(board)) {

                        }





                }
            }
            //maximize
        }else{
            //minimize

        }

        for (Pieces[] row:
             board.board) {
            for (Pieces piece:
                 row) {
                ArrayList<Board> temp = piece.getPossibleMoves(board);


            }
        }

    return null;
    }
}
