package score;

import board.Board;
import pieces.Pieces;

public class Score {
    private int whiteScore;
    private int blackScore;

    public Score(Board board) { // beginning score for each turn between opponent and user
        for (Pieces[] pieceArray :
                board.board) {
            for (Pieces piece :
                    pieceArray) {
                if (piece == null)
                    continue;
                if (piece.getIsWhite()) {
                    whiteScore = whiteScore + ChessType.valueOf(piece.getType().toUpperCase()).getValue();
                } else {

                    blackScore = blackScore + ChessType.valueOf(piece.getType().toUpperCase()).getValue();
                }
            }

        }
    }

    public Score(Score score) {
        this.blackScore = score.blackScore;
        this.whiteScore = score.whiteScore;
    }

    public Score(int whiteScore, int blackScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(int whiteScore) {
        this.whiteScore = whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(int blackScore) {
        this.blackScore = blackScore;
    }

    /*public void updateScore(ChessType capturedType) { // update score based off piece thats captured
        this.score += capturedType.getValue();
    }

    public int getScore() {

        return score;
    }*/
}
