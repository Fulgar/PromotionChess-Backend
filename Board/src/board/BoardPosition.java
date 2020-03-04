package board;

public class BoardPosition {
    public int row;
    public int column;

    public BoardPosition(int r, int c) {
        row = r;
        column = c;
    }

    public BoardPosition(BoardPosition boardPosition) {
        this.row = boardPosition.getRow();
        this.column = boardPosition.getColumn();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int r) {
        row = r;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
