import integradora.structure.StackTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the custom Stack (used for undo functionality).
 * LIFO order, push, pop, peek, isEmpty, size, and exception handling.
 */
class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    // =========================================================================
    // EMPTY STACK
    // =========================================================================

    @Test
    void newStack_isEmpty() {
        assertTrue(stack.isEmpty(), "New stack must be empty");
    }

    @Test
    void newStack_sizeIsZero() {
        assertEquals(0, stack.size());
    }

    @Test
    void pop_onEmptyStack_throwsException() {
        assertThrows(RuntimeException.class, () -> stack.pop(),
                "pop() on empty stack must throw");
    }

    @Test
    void peek_onEmptyStack_throwsException() {
        assertThrows(RuntimeException.class, () -> stack.peek(),
                "peek() on empty stack must throw");
    }

    // =========================================================================
    // PUSH
    // =========================================================================

    @Test
    void afterPush_isNotEmpty() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void afterPush_sizeIncreases() {
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size());
    }

    // =========================================================================
    // POP – LIFO ORDER
    // =========================================================================

    @Test
    void pop_returnsLastPushedElement() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop(), "Last pushed element must come out first");
    }

    @Test
    void pop_lifoOrder() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void pop_decreasesSize() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void pop_lastElement_stackBecomesEmpty() {
        stack.push(42);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    // =========================================================================
    // PEEK
    // =========================================================================

    @Test
    void peek_returnsTopWithoutRemoving() {
        stack.push(5);
        stack.push(99);
        assertEquals(99, stack.peek(), "peek must return top element");
        assertEquals(2, stack.size(), "size must not change after peek");
    }

    // =========================================================================
    // UNDO SIMULATION
    // =========================================================================

    @Test
    void undoSimulation_restoresPreviousState() {
        Stack<int[]> history = new Stack<>();
        int[] state1 = {2, 2};
        int[] state2 = {2, 3};
        int[] state3 = {2, 4};

        history.push(state1);
        history.push(state2);
        history.push(state3);

        int[] undone = history.pop();
        assertArrayEquals(state3, undone);
        assertArrayEquals(state2, history.peek());
    }

    @Test
    void multipleUndos_restoreCorrectOrder() {
        Stack<String> moves = new Stack<>();
        moves.push("RIGHT");
        moves.push("UP");
        moves.push("LEFT");

        assertEquals("LEFT",  moves.pop());
        assertEquals("UP",    moves.pop());
        assertEquals("RIGHT", moves.pop());
        assertTrue(moves.isEmpty());
    }
}

