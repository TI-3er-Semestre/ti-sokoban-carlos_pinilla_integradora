package integradora.controller;

import integradora.model.Board;
import integradora.model.Cell;
import integradora.model.Direction;
import integradora.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest
{

    private GameController stage14;
    private Cell[][] grid;

    @BeforeEach
    void setupStage14()
    {
        Player player = new Player("Carlos", "carlos@mail.com", "carlos123", "avatar1", "beginner");

        grid = new Cell[][]
                {
                        {Cell.WALL,   Cell.WALL,   Cell.WALL,  Cell.WALL,  Cell.WALL},
                        {Cell.WALL,   Cell.PLAYER, Cell.EMPTY, Cell.GOAL,  Cell.WALL},
                        {Cell.WALL,   Cell.EMPTY,  Cell.BOX,   Cell.EMPTY, Cell.WALL},
                        {Cell.WALL,   Cell.WALL,   Cell.WALL,  Cell.WALL,  Cell.WALL}
                };

        stage14 = new GameController(player);
        stage14.startGame(grid);
    }

    // Objetivo: Validar la lógica principal del controlador

    @Test
    void startGame_level1_gameStartsCorrectly()
    {
        // Arrange — stage14 already started

        // Act
        boolean running = stage14.isGameRunning();

        // Assert
        assertTrue(running, "Game should be running after startGame");
        assertNotNull(stage14.getBoard(), "Board should not be null after startGame");
    }

    @Test
    void saveGame_activeGame_stateSavedCorrectly()
    {
        // Arrange — stage14 already started

        // Act
        stage14.saveGame();

        // Assert
        assertNotNull(stage14.getGameState(), "GameState should not be null after saveGame");
        assertEquals(stage14.getBoard().getPlayerRow(),
                stage14.getGameState().getPlayerRow(),
                "Saved player row should match current player row");
    }

    @Test
    void loadGame_savedState_stateLoadedCorrectly()
    {
        // Arrange
        stage14.saveGame();

        // Act
        stage14.loadGame(stage14.getGameState());

        // Assert
        assertTrue(stage14.isGameRunning(), "Game should be running after loadGame");
        assertNotNull(stage14.getBoard(), "Board should not be null after loadGame");
    }

    @Test
    void restartLevel_activeGame_boardResetsToInitialState()
    {
        // Arrange
        stage14.movePlayer(Direction.RIGHT);

        // Act
        stage14.restartLevel(grid);

        // Assert
        assertTrue(stage14.isGameRunning(), "Game should be running after restart");
        assertEquals(0, stage14.getPlayer().getTotalMoves(),
                "Moves should reset to 0 after restartLevel");
    }
}