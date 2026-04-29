package integradora.controller;

import integradora.model.Board;
import integradora.model.Cell;
import integradora.model.Direction;
import integradora.model.GameState;
import integradora.model.Player;

public class GameController
{

    private Board board;
    private Player player;
    private GameState gameState;
    private boolean gameRunning;

    public GameController(Player player)
    {
        this.player     = player;
        this.gameRunning = false;
    }

    public void startGame(Cell[][] grid)
    {
        this.board      = new Board(grid);
        this.gameRunning = true;
    }

    public boolean movePlayer(Direction dir)
    {
        if (!gameRunning || board == null)
        {
            return false;
        }

        boolean moved = board.movePlayer(dir);

        if (moved)
        {
            player.incrementMoves();
            if (board.checkVictory())
            {
                gameRunning = false;
            }
        }

        return moved;
    }

    public boolean checkVictory()
    {
        if (board == null)
        {
            return false;
        }
        return board.checkVictory();
    }

    public void saveGame()
    {
        if (board == null) return;

        this.gameState = new GameState(
                board.getGrid(),
                board.getPlayerRow(),
                board.getPlayerCol(),
                player.getCurrentLevel(),
                player.getTotalMoves(),
                player.getTotalPushes(),
                player.getElapsedTime(),
                player.getUsername()
        );
    }

    public void loadGame(GameState state)
    {
        if (state == null) return;

        this.board = new Board(state.getGrid());
        this.player.setCurrentLevel(state.getCurrentLevel());
        this.gameRunning = true;
    }

    public void restartLevel(Cell[][] originalGrid)
    {
        this.board = new Board(originalGrid);
        player.resetStats();
        this.gameRunning = true;
    }

    public Board getBoard()           {

        return board;

    }
    public Player getPlayer()         {

        return player;

    }
    public GameState getGameState()   {

        return gameState;

    }
    public boolean isGameRunning()    {

        return gameRunning;

    }
}