package integradora.model;

public class Goal {

    private int row;
    private int col;
    private boolean occupied;

    public Goal(int row, int col) {
        this.row      = row;
        this.col      = col;
        this.occupied = false;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    // Getters
    public int getRow() { return row; }
    public int getCol() { return col; }

    // Setters
    public void setRow(int row) { this.row = row; }
    public void setCol(int col) { this.col = col; }
}