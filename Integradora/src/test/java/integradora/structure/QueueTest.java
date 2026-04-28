package integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private Queue<String> stage7; // Cola vacía para validar inserción y extracción de comandos

    @BeforeEach
    void setupStage7() {
        stage7 = new Queue<>();
    }

    // =========================================================================
    // Objetivo: Validar operaciones de la cola de comandos
    // =========================================================================

    @Test
    void enqueue_moveRight_commandAddedToEnd() {
        // Arrange
        String command = "MOVE_RIGHT";

        // Act
        stage7.enqueue(command);

        // Assert
        assertEquals(command, stage7.peek(), "Command should be added at the end of the queue");
        assertEquals(1, stage7.size());
    }

    @Test
    void dequeue_afterEnqueue_returnsAndRemovesFirstCommand() {
        // Arrange
        stage7.enqueue("MOVE_RIGHT");
        stage7.enqueue("MOVE_UP");

        // Act
        String result = stage7.dequeue();

        // Assert
        assertEquals("MOVE_RIGHT", result, "Dequeue should return and remove the first command inserted");
        assertEquals(1, stage7.size(), "Size should decrease after dequeue");
    }

    @Test
    void peek_afterEnqueue_returnsFirstElementWithoutRemoving() {
        // Arrange
        stage7.enqueue("MOVE_RIGHT");
        stage7.enqueue("MOVE_UP");

        // Act
        String result = stage7.peek();

        // Assert
        assertEquals("MOVE_RIGHT", result, "Peek should return the first element without removing it");
        assertEquals(2, stage7.size(), "Size should not change after peek");
    }

    @Test
    void isEmpty_emptyQueue_returnsTrue() {
        // Arrange — stage7 is already empty

        // Act
        boolean empty = stage7.isEmpty();

        // Assert
        assertTrue(empty, "isEmpty should return true when queue is empty");
    }
}
