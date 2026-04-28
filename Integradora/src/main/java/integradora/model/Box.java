package integradora.model;

public class Box {

    private int row;
    private int col;
    private boolean onGoal;

    public Box(int row, int col) {
        this.row    = row;
        this.col    = col;
        this.onGoal = false;
    }

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    public boolean isOnGoal() {
        return onGoal;
    }

    public void setOnGoal(boolean onGoal) {
        this.onGoal = onGoal;
    }

    // Getters
    public int getRow() { return row; }
    public int getCol() { return col; }

    // Setters
    public void setRow(int row) { this.row = row; }
    public void setCol(int col) { this.col = col; }
}