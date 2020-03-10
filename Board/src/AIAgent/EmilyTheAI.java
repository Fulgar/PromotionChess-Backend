package AIAgent;


import board.Board;
import pieces.Pieces;
import score.Score;

import java.util.ArrayList;

public class EmilyTheAI {
    private int searthDepth = 4;
    private String currentBestMove = null;


    public EmilyTheAI() {
    }


    public Board minimax(Board board, Board alpha, Board beta, boolean isMaximizingPlayer, int searthDepth, boolean isMovingPlayerWhite) {
        Board currentBestMove;
        int bestValue;
        if (searthDepth == 0) {
            return board;
        }

        if (isMaximizingPlayer) {
            Board maxEval = new Board();
            maxEval.setScore(new Score(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2));
            for (Pieces[] row :
                    board.board) {
                for (Pieces piece :
                        row) {
                    if (piece ==null) break;
                    if (piece.getIsWhite() && isMovingPlayerWhite) {
                        for (Board currentPossibleMove :
                                piece.getPossibleMoves(board)) {
                            currentPossibleMove.printBoard();
                            Board eval = minimax(currentPossibleMove, alpha, beta, false, searthDepth- 1, !isMovingPlayerWhite);
                            maxEval = max(maxEval, eval);
                            alpha = max(alpha, eval);
                            if (beta == null) break;
                            if (alpha == null) break;
                            if (beta.getAIScore() <= alpha.getAIScore())
                                break;
                            return maxEval;

                        }
                    }
                }
            }
            //maximize
        } else {
            Board minEval = new Board();
            minEval.setScore(new Score(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2));
            for (Pieces[] row :
                    board.board) {
                for (Pieces piece :
                        row) {
                    if (piece == null) break;
                    if (piece.getIsWhite() && isMovingPlayerWhite) {
                        for (Board currentPossibleMove :
                                piece.getPossibleMoves(board)) {
                            Board eval = minimax(currentPossibleMove, alpha, beta, false, searthDepth- 1, !isMovingPlayerWhite);
                            minEval = min(minEval, eval);
                            alpha = min(beta, eval);
                            if (beta.getAIScore() <= alpha.getAIScore())
                                break;
                            return minEval;

                        }
                    }
                }
            }
        }
        return null;
    }

    private Board min(Board minEval, Board eval) {
        if (minEval == null) return minEval;
        if (eval == null) return eval;
        if (minEval.getAIScore() <= eval.getAIScore())
            return minEval;
        else
            return eval;
    }

    private Board max(Board left, Board right) {
        if (left == null) return right;
        if (right == null) return left;
        if (left.getAIScore() >= right.getAIScore())
            return left;
        else
            return right;
    }
}
