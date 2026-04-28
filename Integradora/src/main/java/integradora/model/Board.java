package integradora.model;

public class Board {

    private Cell[][] grid;
    private int playerRow;
    private int playerCol;

    public Board(Cell[][] grid) {
        this.grid = grid;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == Cell.PLAYER || grid[r][c] == Cell.PLAYER_ON_GOAL) {
                    playerRow = r;
                    playerCol = c;
                }
            }
        }
    }

    public boolean movePlayer(Direction dir) {
        return false; // TODO: implement
    }

    public boolean checkVictory() {
        return false; // TODO: implement
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getPlayerRow() { return playerRow; }
    public int getPlayerCol() { return playerCol; }
}