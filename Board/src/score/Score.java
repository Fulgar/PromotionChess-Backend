package score;

public class Score {
    private int score;

    public Score() { // beginning score for each turn between opponent and user
        this.score = 0;
    }

    public void updateScore(ChessType capturedType) { // update score based off piece thats captured
        this.score += capturedType.getValue();
    }

    public int getScore() {

        return score;
    }
}
