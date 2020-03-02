package score;

public enum ChessType {

    KING(500),
    QUEEN(9),
    KNIGHT(5),
    BISHOP(3),
    ROOK(3),
    PAWN(1);

    private int value;

    ChessType(int value) { // chess piece static values
        this.value = value;
    }


    public int getValue() {
        return value;
    }
}
