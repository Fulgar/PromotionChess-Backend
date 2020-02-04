public abstract class Pieces {
    String type, color;
    int score;
    String moves[];
    int[] positionOnBoard; // Possibly make a position class or get position from the board

    public abstract Board[] getPossibleMoves(Board currentBoard);
}
