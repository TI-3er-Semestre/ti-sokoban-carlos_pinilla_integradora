package integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<String, String> stage9; // Tabla hash vacía para almacenamiento de estados visitados

    @BeforeEach
    void setupStage9() {
        stage9 = new HashTable<>();
    }


    // Objetivo: Validar el almacenamiento de estados visitados en la tabla hash


    @Test
    void put_boardState_stateStoredCorrectly() {
        // Arrange
        String key   = "2,2|3,4";
        String state = "visited";

        // Act
        stage9.put(key, state);

        // Assert
        assertEquals(state, stage9.get(key), "State should be stored and retrievable by key");
        assertEquals(1, stage9.size());
    }

    @Test
    void get_existingKey_returnsAssociatedState() {
        // Arrange
        stage9.put("1,1|2,3", "stateA");
        stage9.put("2,2|3,4", "stateB");

        // Act
        String result = stage9.get("2,2|3,4");

        // Assert
        assertEquals("stateB", result, "Get should return the state associated with the existing key");
    }

    @Test
    void containsKey_existingKey_returnsTrueIfStateWasVisited() {
        // Arrange
        stage9.put("3,3|4,5", "visited");

        // Act
        boolean contains = stage9.containsKey("3,3|4,5");

        // Assert
        assertTrue(contains, "containsKey should return true if the state was already visited");
    }

    @Test
    void remove_existingKey_deletesStoredState() {
        // Arrange
        stage9.put("1,1|2,2", "visited");

        // Act
        boolean removed = stage9.remove("1,1|2,2");

        // Assert
        assertTrue(removed, "Remove should return true for an existing key");
        assertNull(stage9.get("1,1|2,2"), "State should no longer exist after removal");
        assertEquals(0, stage9.size());
    }
}
