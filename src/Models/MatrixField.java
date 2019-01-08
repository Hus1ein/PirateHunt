package Models;

public class MatrixField {

    protected int column;
    protected int row;
    protected int status;
    public static final int PLAYER = 0;
    public static final int ENEMY = 1;
    public static final int EMPTY = 2;
    public static final int BARRIER = 3;
    public static final int TARGET = 4;

    public MatrixField(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public MatrixField(int row, int column, int status) {
        this.row = row;
        this.column = column;
        this.status = status;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
