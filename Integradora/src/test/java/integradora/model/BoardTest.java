package integradora.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board stage1; // Tablero básico 5x5 con jugador, una caja, un objetivo y espacios libres
    private Board stage2; // Jugador frente a un muro para validar movimientos inválidos
    private Board stage3; // Jugador detrás de una caja con espacio libre adelante
    private Board stage4; // Caja bloqueada por un muro para validar empuje inválido
    private Board stage5; // Todas las cajas ubicadas sobre objetivos para validar victoria

    @BeforeEach
    void setupStage1() {
        // W W W W W
        // W P . . W
        // W . B G W
        // W . . . W
        // W W W W W
        Cell[][] grid = {
                {Cell.WALL,   Cell.WALL,  Cell.WALL,  Cell.WALL, Cell.WALL},
                {Cell.WALL,   Cell.PLAYER,Cell.EMPTY, Cell.EMPTY,Cell.WALL},
                {Cell.WALL,   Cell.EMPTY, Cell.BOX,   Cell.GOAL, Cell.WALL},
                {Cell.WALL,   Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,Cell.WALL},
                {Cell.WALL,   Cell.WALL,  Cell.WALL,  Cell.WALL, Cell.WALL}
        };
        stage1 = new Board(grid);
    }

    @BeforeEach
    void setupStage2() {
        // W W W W W
        // W W W W W
        // W W P W W
        // W W W W W
        // W W W W W
        Cell[][] grid = {
                {Cell.WALL, Cell.WALL,   Cell.WALL,   Cell.WALL, Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.WALL,   Cell.WALL, Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.PLAYER, Cell.WALL, Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.WALL,   Cell.WALL, Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.WALL,   Cell.WALL, Cell.WALL}
        };
        stage2 = new Board(grid);
    }

    @BeforeEach
    void setupStage3() {
        // W W W W W
        // W P B . W
        // W W W W W
        Cell[][] grid = {
                {Cell.WALL, Cell.WALL,   Cell.WALL, Cell.EMPTY, Cell.WALL},
                {Cell.WALL, Cell.PLAYER, Cell.BOX,  Cell.EMPTY, Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.WALL, Cell.WALL,  Cell.WALL}
        };
        stage3 = new Board(grid);
    }

    @BeforeEach
    void setupStage4() {
        // W W W W
        // W P B W
        // W W W W
        Cell[][] grid = {
                {Cell.WALL, Cell.WALL,   Cell.WALL, Cell.WALL},
                {Cell.WALL, Cell.PLAYER, Cell.BOX,  Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.WALL, Cell.WALL}
        };
        stage4 = new Board(grid);
    }

    @BeforeEach
    void setupStage5() {
        // W W W W
        // W P BG W
        // W W W W
        Cell[][] grid = {
                {Cell.WALL, Cell.WALL,   Cell.WALL,          Cell.WALL},
                {Cell.WALL, Cell.PLAYER, Cell.BOX_ON_GOAL,   Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.WALL,          Cell.WALL}
        };
        stage5 = new Board(grid);
    }

    // =========================================================================
    // Objetivo: Validar movimientos válidos e inválidos del jugador
    // =========================================================================

    @Test
    void movePlayer_right_playerMovesCorrectly() {
        // Arrange
        int expectedCol = stage1.getPlayerCol() + 1;

        // Act
        boolean moved = stage1.movePlayer(Direction.RIGHT);

        // Assert
        assertTrue(moved, "Player should move right into empty cell");
        assertEquals(expectedCol, stage1.getPlayerCol());
    }

    @Test
    void movePlayer_upIntoWall_playerStaysInPlace() {
        // Arrange
        int expectedRow = stage2.getPlayerRow();

        // Act
        boolean moved = stage2.movePlayer(Direction.UP);

        // Assert
        assertFalse(moved, "Player should not move through a wall");
        assertEquals(expectedRow, stage2.getPlayerRow());
    }

    @Test
    void movePlayer_left_playerMovesCorrectly() {
        // Arrange
        stage1.movePlayer(Direction.RIGHT); // move right first so left is valid
        int expectedCol = stage1.getPlayerCol() - 1;

        // Act
        boolean moved = stage1.movePlayer(Direction.LEFT);

        // Assert
        assertTrue(moved, "Player should move left into empty cell");
        assertEquals(expectedCol, stage1.getPlayerCol());
    }

    @Test
    void movePlayer_downIntoWall_movementRejected() {
        // Arrange
        int expectedRow = stage2.getPlayerRow();

        // Act
        boolean moved = stage2.movePlayer(Direction.DOWN);

        // Assert
        assertFalse(moved, "Movement should be rejected due to wall collision");
        assertEquals(expectedRow, stage2.getPlayerRow());
    }


    // Objetivo: Validar el empuje correcto de las cajas

    @Test
    void pushBox_right_boxMovesAndPlayerAdvances() {
        // Arrange
        int expectedPlayerCol = stage3.getPlayerCol() + 1;
        int expectedBoxCol    = stage3.getPlayerCol() + 2;

        // Act
        boolean moved = stage3.movePlayer(Direction.RIGHT);

        // Assert
        assertTrue(moved, "Player should push box to the right");
        assertEquals(expectedPlayerCol, stage3.getPlayerCol());
        assertEquals(Cell.BOX, stage3.getCell(1, expectedBoxCol));
    }

    @Test
    void pushBox_left_boxMovesCorrectlyToLeft() {
        // Arrange
        // Rearrange so player is to the right of the box
        Cell[][] grid = {
                {Cell.WALL, Cell.WALL,  Cell.WALL,   Cell.WALL},
                {Cell.WALL, Cell.EMPTY, Cell.BOX,    Cell.WALL},
                {Cell.WALL, Cell.WALL,  Cell.PLAYER, Cell.WALL},
                {Cell.WALL, Cell.WALL,  Cell.WALL,   Cell.WALL}
        };
        // Player above box pushing down is left scenario analog — use right-to-left layout
        Cell[][] grid2 = {
                {Cell.WALL, Cell.WALL,  Cell.WALL,   Cell.WALL,  Cell.WALL},
                {Cell.WALL, Cell.EMPTY, Cell.BOX,    Cell.PLAYER,Cell.WALL},
                {Cell.WALL, Cell.WALL,  Cell.WALL,   Cell.WALL,  Cell.WALL}
        };
        Board boardLeft = new Board(grid2);
        int expectedBoxCol = 1;

        // Act
        boolean moved = boardLeft.movePlayer(Direction.LEFT);

        // Assert
        assertTrue(moved, "Player should push box to the left");
        assertEquals(Cell.BOX, boardLeft.getCell(1, expectedBoxCol));
    }

    @Test
    void pushBox_rightIntoWall_boxDoesNotMove() {
        // Arrange
        int playerColBefore = stage4.getPlayerCol();
        int boxColBefore    = playerColBefore + 1;

        // Act
        boolean moved = stage4.movePlayer(Direction.RIGHT);

        // Assert
        assertFalse(moved, "Box should not move because there is a wall behind it");
        assertEquals(Cell.BOX, stage4.getCell(1, boxColBefore));
        assertEquals(playerColBefore, stage4.getPlayerCol());
    }

    @Test
    void pushBox_upIntoWall_stateDoesNotChange() {
        // Arrange
        Cell[][] grid = {
                {Cell.WALL, Cell.WALL,   Cell.WALL},
                {Cell.WALL, Cell.BOX,    Cell.WALL},
                {Cell.WALL, Cell.PLAYER, Cell.WALL},
                {Cell.WALL, Cell.WALL,   Cell.WALL}
        };
        Board boardUp = new Board(grid);
        int playerRowBefore = boardUp.getPlayerRow();

        // Act
        boolean moved = boardUp.movePlayer(Direction.UP);

        // Assert
        assertFalse(moved, "Move should be invalid: wall is behind the box");
        assertEquals(playerRowBefore, boardUp.getPlayerRow());
    }

    // =========================================================================
    // Objetivo: Validar la detección de victoria
    // =========================================================================

    @Test
    void checkVictory_allBoxesOnGoals_returnsTrue() {
        // Arrange — stage5 starts with all boxes on goals

        // Act
        boolean victory = stage5.checkVictory();

        // Assert
        assertTrue(victory, "Should return true because all boxes are on goals");
    }

    @Test
    void checkVictory_boxNotOnGoal_returnsFalse() {
        // Arrange — stage1 has box NOT on goal

        // Act
        boolean victory = stage1.checkVictory();

        // Assert
        assertFalse(victory, "Should return false because box is not on goal yet");
    }
}
