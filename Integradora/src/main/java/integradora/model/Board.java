package integradora.model;

public class Board {

    private Cell[][] grid;
    private int playerRow;
    private int playerCol;
    private int totalGoals;
    private int boxesOnGoal;
    private int rows;
    private int cols;

    public Board(Cell[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.grid = new Cell[rows][cols];
        this.totalGoals = 0;
        this.boxesOnGoal = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                this.grid[r][c] = grid[r][c];
                if (grid[r][c] == Cell.PLAYER || grid[r][c] == Cell.PLAYER_ON_GOAL) {
                    playerRow = r;
                    playerCol = c;
                }
                if (grid[r][c] == Cell.GOAL || grid[r][c] == Cell.PLAYER_ON_GOAL) {
                    totalGoals++;
                }
                if (grid[r][c] == Cell.BOX_ON_GOAL) {
                    totalGoals++;
                    boxesOnGoal++;
                }
            }
        }
    }

    public boolean movePlayer(Direction dir) {
        int dr = 0, dc = 0;
        switch (dir) {
            case UP:    dr = -1; break;
            case DOWN:  dr =  1; break;
            case LEFT:  dc = -1; break;
            case RIGHT: dc =  1; break;
        }

        int newRow = playerRow + dr;
        int newCol = playerCol + dc;

        if (!inBounds(newRow, newCol)) return false;

        Cell target = grid[newRow][newCol];

        if (target == Cell.WALL) return false;

        if (target == Cell.BOX || target == Cell.BOX_ON_GOAL) {
            int boxNewRow = newRow + dr;
            int boxNewCol = newCol + dc;

            if (!inBounds(boxNewRow, boxNewCol)) return false;

            Cell behindBox = grid[boxNewRow][boxNewCol];
            if (behindBox == Cell.WALL || behindBox == Cell.BOX || behindBox == Cell.BOX_ON_GOAL) return false;

            boolean boxWasOnGoal  = (target == Cell.BOX_ON_GOAL);
            boolean boxLandsOnGoal = (behindBox == Cell.GOAL);

            grid[boxNewRow][boxNewCol] = boxLandsOnGoal ? Cell.BOX_ON_GOAL : Cell.BOX;

            if (boxLandsOnGoal) boxesOnGoal++;
            if (boxWasOnGoal)   boxesOnGoal--;
        }

        boolean playerWasOnGoal  = (grid[playerRow][playerCol] == Cell.PLAYER_ON_GOAL);
        grid[playerRow][playerCol] = playerWasOnGoal ? Cell.GOAL : Cell.EMPTY;

        Cell currentTarget = grid[newRow][newCol];
        grid[newRow][newCol] = (currentTarget == Cell.GOAL) ? Cell.PLAYER_ON_GOAL : Cell.PLAYER;

        playerRow = newRow;
        playerCol = newCol;

        return true;
    }

    public boolean checkVictory() {
        return totalGoals > 0 && boxesOnGoal == totalGoals;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getPlayerRow()  { return playerRow; }
    public int getPlayerCol()  { return playerCol; }
    public int getTotalGoals() { return totalGoals; }
    public int getBoxesOnGoal(){ return boxesOnGoal; }
    public int getRows()       { return rows; }
    public int getCols()       { return cols; }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
    public Cell[][] getGrid()
    {
        return grid;
    }
}