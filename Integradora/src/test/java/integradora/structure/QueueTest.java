package integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private Queue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void newQueue_isEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void newQueue_sizeIsZero() {
        assertEquals(0, queue.size());
    }

    @Test
    void dequeue_onEmptyQueue_throwsException() {
        assertThrows(RuntimeException.class, () -> queue.dequeue());
    }

    @Test
    void peek_onEmptyQueue_throwsException() {
        assertThrows(RuntimeException.class, () -> queue.peek());
    }

    @Test
    void afterEnqueue_isNotEmpty() {
        queue.enqueue("RIGHT");
        assertFalse(queue.isEmpty());
    }

    @Test
    void afterEnqueue_sizeIncreases() {
        queue.enqueue("UP");
        queue.enqueue("DOWN");
        assertEquals(2, queue.size());
    }

    @Test
    void dequeue_returnsFirstEnqueuedElement() {
        queue.enqueue("UP");
        queue.enqueue("RIGHT");
        queue.enqueue("DOWN");
        assertEquals("UP", queue.dequeue());
    }

    @Test
    void dequeue_fifoOrder() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
    }

    @Test
    void dequeue_decreasesSize() {
        queue.enqueue("X");
        queue.enqueue("Y");
        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    void dequeue_lastElement_queueBecomesEmpty() {
        queue.enqueue("ONLY");
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void peek_returnsFrontWithoutRemoving() {
        queue.enqueue("FIRST");
        queue.enqueue("SECOND");
        assertEquals("FIRST", queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    void inputBuffer_processesFastKeyPresses_inOrder() {
        Queue<String> buffer = new Queue<>();
        buffer.enqueue("RIGHT");
        buffer.enqueue("RIGHT");
        buffer.enqueue("UP");
        buffer.enqueue("LEFT");
        assertEquals("RIGHT", buffer.dequeue());
        assertEquals("RIGHT", buffer.dequeue());
        assertEquals("UP",    buffer.dequeue());
        assertEquals("LEFT",  buffer.dequeue());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void inputBuffer_interleavedEnqueueDequeue() {
        Queue<Integer> buffer = new Queue<>();
        buffer.enqueue(1);
        buffer.enqueue(2);
        assertEquals(1, buffer.dequeue());
        buffer.enqueue(3);
        assertEquals(2, buffer.dequeue());
        assertEquals(3, buffer.dequeue());
        assertTrue(buffer.isEmpty());
    }
}
