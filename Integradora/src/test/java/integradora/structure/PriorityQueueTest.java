package integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue<PlayerScore> stage8; // Cola de prioridad con resultados de jugadores

    // Helper class to simulate a player score for the leaderboard
    static class PlayerScore implements Comparable<PlayerScore> {
        String name;
        int moves;

        PlayerScore(String name, int moves) {
            this.name  = name;
            this.moves = moves;
        }

        @Override
        public int compareTo(PlayerScore other) {
            return Integer.compare(this.moves, other.moves); // fewer moves = better
        }
    }

    @BeforeEach
    void setupStage8() {
        stage8 = new PriorityQueue<>();
        stage8.enqueue(new PlayerScore("Alice", 45));
        stage8.enqueue(new PlayerScore("Bob",   30));
        stage8.enqueue(new PlayerScore("Carol", 60));
    }


    // Objetivo: Validar la cola de prioridad para el leaderboard


    @Test
    void enqueue_playerWith20Moves_insertedAccordingToPriority() {
        // Arrange
        PlayerScore newPlayer = new PlayerScore("Dave", 20);

        // Act
        stage8.enqueue(newPlayer);

        // Assert
        assertEquals("Dave", stage8.peek().name, "Player with fewest moves should be at the top");
        assertEquals(4, stage8.size());
    }

    @Test
    void dequeue_afterInserts_returnsPlayerWithBestScore() {
        // Arrange — stage8 has Alice(45), Bob(30), Carol(60)

        // Act
        PlayerScore best = stage8.dequeue();

        // Assert
        assertEquals("Bob", best.name, "Dequeue should return the player with the best score (fewest moves)");
        assertEquals(30, best.moves);
    }

    @Test
    void peek_afterInserts_returnsBestPlayerWithoutRemoving() {
        // Arrange — stage8 has Alice(45), Bob(30), Carol(60)

        // Act
        PlayerScore best = stage8.peek();

        // Assert
        assertEquals("Bob", best.name, "Peek should return best player without removing them");
        assertEquals(3, stage8.size(), "Size should not change after peek");
    }
}
