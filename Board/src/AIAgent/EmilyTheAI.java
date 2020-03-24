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
                    if (piece == null) continue;
                    if (piece.getIsWhite() == isMovingPlayerWhite) {
                        for (Board currentPossibleMove :
                                piece.getPossibleMoves(board)) {
                            //currentPossibleMove.printBoard();
                            Board eval = minimax(currentPossibleMove, alpha, beta, false, searthDepth- 1, !isMovingPlayerWhite);
                            maxEval = max(maxEval, eval);
                            alpha.copyObjectKeepReference(max(alpha, eval));
                            if (beta == null) continue;
                            if (alpha == null) continue;

                            if (beta.getAIScore() >= alpha.getAIScore()) {
                                Board pruneNode = new Board();
                                pruneNode.setScore(new Score(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2));
                                System.out.println("Node Pruned");
                                return eval;
                            }


                        }

                    }
                }
            }
            System.out.println("___________________________________________________________");
            System.out.println("Maximizing move found.");
            maxEval.printBoard();
            System.out.println("___________________________________________________________");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return maxEval;
        } else {
            System.out.println("minimizing player");
            Board minEval = new Board();
            minEval.setScore(new Score(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2));
            for (Pieces[] row :
                    board.board) {
                for (Pieces piece :
                        row) {
                    if (piece == null) continue;
                    if (piece.getIsWhite() == isMovingPlayerWhite) {
                        for (Board currentPossibleMove :
                                piece.getPossibleMoves(board)) {
                            //currentPossibleMove.printBoard();
                            Board eval = minimax(currentPossibleMove, alpha, beta, true, searthDepth- 1, !isMovingPlayerWhite);
                            minEval = min(minEval, eval);
                            beta.copyObjectKeepReference(min(beta, eval));
                            if (beta == null) continue;
                            if (alpha == null) continue;
                            if (beta.getAIScore() >= alpha.getAIScore()){
                                Board pruneNode = new Board();
                                pruneNode.setScore(new Score(Integer.MAX_VALUE/2,Integer.MAX_VALUE/2));
                                System.out.println("Node Pruned");
                                return eval;
                            }



                        }

                    }
                }
            }
            System.out.println("___________________________________________________________");
            System.out.println("minimizing move found.");
            minEval.printBoard();
            System.out.println("___________________________________________________________");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return minEval;
        }

    }

    private Board min(Board minEval, Board eval) {
        if (minEval.getScore().getWhiteScore() == Integer.MAX_VALUE/2) return eval;
        if (minEval.getAIScore() <= eval.getAIScore())
            return minEval;
        else
            return eval;
    }

    private Board max(Board left, Board right) {
        if (left.getScore().getWhiteScore() == Integer.MIN_VALUE/2) return right;
        if (left.getAIScore() >= right.getAIScore())
            return left;
        else
            return right;
    }
}
