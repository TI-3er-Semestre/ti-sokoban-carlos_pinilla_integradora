ackage integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @BeforeEach
    void setUp() {
        pq = new PriorityQueue<>();
    }

    @Test
    void newPQ_isEmpty() {
        assertTrue(pq.isEmpty());
    }

    @Test
    void newPQ_sizeIsZero() {
        assertEquals(0, pq.size());
    }

    @Test
    void dequeue_onEmptyPQ_throwsException() {
        assertThrows(RuntimeException.class, () -> pq.dequeue());
    }

    @Test
    void peek_onEmptyPQ_throwsException() {
        assertThrows(RuntimeException.class, () -> pq.peek());
    }

    @Test
    void afterEnqueue_isNotEmpty() {
        pq.enqueue(5);
        assertFalse(pq.isEmpty());
    }

    @Test
    void afterEnqueue_sizeIncreases() {
        pq.enqueue(3);
        pq.enqueue(1);
        assertEquals(2, pq.size());
    }

    @Test
    void dequeue_returnsMinimumElement() {
        pq.enqueue(10);
        pq.enqueue(3);
        pq.enqueue(7);
        assertEquals(3, pq.dequeue());
    }

    @Test
    void dequeue_returnsElementsInAscendingOrder() {
        pq.enqueue(5);
        pq.enqueue(1);
        pq.enqueue(4);
        pq.enqueue(2);
        pq.enqueue(3);
        assertEquals(1, pq.dequeue());
        assertEquals(2, pq.dequeue());
        assertEquals(3, pq.dequeue());
        assertEquals(4, pq.dequeue());
        assertEquals(5, pq.dequeue());
    }

    @Test
    void peek_returnsMinWithoutRemoving() {
        pq.enqueue(8);
        pq.enqueue(2);
        pq.enqueue(5);
        assertEquals(2, pq.peek());
        assertEquals(3, pq.size());
    }

    @Test
    void dequeue_decreasesSize() {
        pq.enqueue(1);
        pq.enqueue(2);
        pq.dequeue();
        assertEquals(1, pq.size());
    }

    @Test
    void insertDuplicates_handledCorrectly() {
        pq.enqueue(3);
        pq.enqueue(3);
        pq.enqueue(3);
        assertEquals(3, pq.size());
        assertEquals(3, pq.dequeue());
        assertEquals(3, pq.dequeue());
        assertEquals(3, pq.dequeue());
        assertTrue(pq.isEmpty());
    }

    // Clase auxiliar para simular leaderboard
    static class Score implements Comparable<Score> {
        String player;
        int moves;
        Score(String player, int moves) { this.player = player; this.moves = moves; }
        @Override
        public int compareTo(Score o) { return Integer.compare(this.moves, o.moves); }
    }

    @Test
    void leaderboard_bestScoreFirst() {
        PriorityQueue<Score> leaderboard = new PriorityQueue<>();
        leaderboard.enqueue(new Score("Alice", 45));
        leaderboard.enqueue(new Score("Bob",   28));
        leaderboard.enqueue(new Score("Carol", 63));
        Score best = leaderboard.dequeue();
        assertEquals("Bob", best.player);
        assertEquals(28, best.moves);
    }

    @Test
    void leaderboard_topKResults() {
        PriorityQueue<Score> leaderboard = new PriorityQueue<>();
        leaderboard.enqueue(new Score("P1", 50));
        leaderboard.enqueue(new Score("P2", 10));
        leaderboard.enqueue(new Score("P3", 30));
        leaderboard.enqueue(new Score("P4", 20));
        leaderboard.enqueue(new Score("P5", 40));
        assertEquals(10, leaderboard.dequeue().moves);
        assertEquals(20, leaderboard.dequeue().moves);
        assertEquals(30, leaderboard.dequeue().moves);
    }

    @Test
    void leaderboard_newScoreBeatsExisting_appearsFirst() {
        PriorityQueue<Score> leaderboard = new PriorityQueue<>();
        leaderboard.enqueue(new Score("Old", 100));
        leaderboard.enqueue(new Score("New",   5));
        assertEquals("New", leaderboard.peek().player);
    }

    @Test
    void largeInserts_maintainCorrectOrder() {
        PriorityQueue<Integer> big = new PriorityQueue<>();
        for (int i = 100; i >= 1; i--) big.enqueue(i);
        for (int expected = 1; expected <= 100; expected++) {
            assertEquals(expected, big.dequeue());
        }
    }
}
 