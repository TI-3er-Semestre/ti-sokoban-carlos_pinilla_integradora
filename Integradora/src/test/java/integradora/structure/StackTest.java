package integradora.structure;

import integradora.structure.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private Stack<String> stage6; // Pila vacía para validar operaciones básicas

    @BeforeEach
    void setupStage6() {
        stage6 = new Stack<>();
    }

    // =========================================================================
    // Objetivo: Validar las operaciones de pila para el funcionamiento Undo
    // =========================================================================

    @Test
    void push_boardState_elementAddedToTop() {
        // Arrange
        String boardState = "2,2|3,4";

        // Act
        stage6.push(boardState);

        // Assert
        assertEquals(boardState, stage6.peek(), "Element should be at the top after push");
        assertEquals(1, stage6.size());
    }

    @Test
    void pop_afterPush_returnsAndRemovesLastInsertedState() {
        // Arrange
        stage6.push("state1");
        stage6.push("state2");

        // Act
        String result = stage6.pop();

        // Assert
        assertEquals("state2", result, "Pop should return the last inserted state");
        assertEquals(1, stage6.size(), "Size should decrease after pop");
    }

    @Test
    void peek_afterPush_returnsTopWithoutRemoving() {
        // Arrange
        stage6.push("state1");
        stage6.push("state2");

        // Act
        String result = stage6.peek();

        // Assert
        assertEquals("state2", result, "Peek should return top element without removing it");
        assertEquals(2, stage6.size(), "Size should not change after peek");
    }

    @Test
    void isEmpty_emptyStack_returnsTrue() {
        // Arrange — stage6 is already empty

        // Act
        boolean empty = stage6.isEmpty();

        // Assert
        assertTrue(empty, "isEmpty should return true when stack is empty");
    }
}

