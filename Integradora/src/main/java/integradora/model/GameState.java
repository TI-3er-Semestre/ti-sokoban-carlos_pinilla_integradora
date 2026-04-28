package integradora.model;

import java.io.Serializable;

public class GameState implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Cell[][] grid;
    private int playerRow;
    private int playerCol;
    private int currentLevel;
    private int totalMoves;
    private int totalPushes;
    private long elapsedTime;
    private String playerUsername;

    public GameState(Cell[][] grid, int playerRow, int playerCol,
                     int currentLevel, int totalMoves, int totalPushes,
                     long elapsedTime, String playerUsername)
    {
        this.grid           = grid;
        this.playerRow      = playerRow;
        this.playerCol      = playerCol;
        this.currentLevel   = currentLevel;
        this.totalMoves     = totalMoves;
        this.totalPushes    = totalPushes;
        this.elapsedTime    = elapsedTime;
        this.playerUsername = playerUsername;
    }

    // Getters
    public Cell[][] getGrid()
    {
        return grid;
    }

    public int getPlayerRow()
    {
        return playerRow;
    }

    public int getPlayerCol()
    {
        return playerCol;
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public int getTotalMoves()
    {
        return totalMoves;
    }

    public int getTotalPushes()
    {
        return totalPushes;
    }

    public long getElapsedTime()
    {
        return elapsedTime;
    }

    public String getPlayerUsername()
    {
        return playerUsername;
    }

    // Setters
    public void setGrid(Cell[][] grid)
    {
        this.grid = grid;
    }

    public void setPlayerRow(int playerRow)
    {
        this.playerRow = playerRow;
    }

    public void setPlayerCol(int playerCol)
    {
        this.playerCol = playerCol;
    }

    public void setCurrentLevel(int currentLevel)
    {
        this.currentLevel = currentLevel;
    }

    public void setTotalMoves(int totalMoves)
    {
        this.totalMoves = totalMoves;
    }

    public void setTotalPushes(int totalPushes)
    {
        this.totalPushes = totalPushes;
    }

    public void setElapsedTime(long elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }

    public void setPlayerUsername(String username)
    {
        this.playerUsername = username;
    }
}