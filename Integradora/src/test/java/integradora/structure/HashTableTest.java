package integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<String, Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    @Test
    void newTable_isEmpty() {
        assertTrue(table.isEmpty());
    }

    @Test
    void newTable_sizeIsZero() {
        assertEquals(0, table.size());
    }

    @Test
    void get_onMissingKey_returnsNull() {
        assertNull(table.get("nonexistent"));
    }

    @Test
    void containsKey_onMissingKey_returnsFalse() {
        assertFalse(table.containsKey("ghost"));
    }

    @Test
    void put_thenGet_returnsCorrectValue() {
        table.put("state1", 42);
        assertEquals(42, table.get("state1"));
    }

    @Test
    void put_multipleEntries_allRetrievable() {
        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);
        assertEquals(1, table.get("a"));
        assertEquals(2, table.get("b"));
        assertEquals(3, table.get("c"));
    }

    @Test
    void put_updatesExistingKey() {
        table.put("key", 10);
        table.put("key", 99);
        assertEquals(99, table.get("key"));
        assertEquals(1, table.size());
    }

    @Test
    void put_increasesSize() {
        table.put("x", 1);
        table.put("y", 2);
        assertEquals(2, table.size());
    }

    @Test
    void afterPut_containsKey_returnsTrue() {
        table.put("hello", 0);
        assertTrue(table.containsKey("hello"));
    }

    @Test
    void remove_existingKey_returnsTrue() {
        table.put("del", 7);
        assertTrue(table.remove("del"));
    }

    @Test
    void remove_existingKey_decreasesSize() {
        table.put("del", 7);
        table.remove("del");
        assertEquals(0, table.size());
    }

    @Test
    void remove_existingKey_keyNoLongerFound() {
        table.put("gone", 5);
        table.remove("gone");
        assertNull(table.get("gone"));
        assertFalse(table.containsKey("gone"));
    }

    @Test
    void remove_missingKey_returnsFalse() {
        assertFalse(table.remove("missing"));
    }

    @Test
    void collision_bothEntriesRetrievable() {
        // "Aa" y "BB" tienen el mismo hashCode en Java
        HashTable<String, String> t = new HashTable<>();
        t.put("Aa", "value1");
        t.put("BB", "value2");
        assertEquals("value1", t.get("Aa"));
        assertEquals("value2", t.get("BB"));
    }

    @Test
    void transpositionTable_detectsVisitedState() {
        HashTable<String, Boolean> visited = new HashTable<>();
        String state = "2,2|3,4";
        assertFalse(visited.containsKey(state));
        visited.put(state, true);
        assertTrue(visited.containsKey(state));
    }

    @Test
    void transpositionTable_differentStates_storedSeparately() {
        HashTable<String, Boolean> visited = new HashTable<>();
        visited.put("2,2|3,4", true);
        visited.put("2,3|3,4", true);
        visited.put("1,2|3,4", true);
        assertTrue(visited.containsKey("2,2|3,4"));
        assertTrue(visited.containsKey("2,3|3,4"));
        assertTrue(visited.containsKey("1,2|3,4"));
        assertFalse(visited.containsKey("0,0|0,0"));
        assertEquals(3, visited.size());
    }

    @Test
    void transpositionTable_preventsReexploration() {
        HashTable<String, Boolean> visited = new HashTable<>();
        String state = "1,1|2,2";
        assertFalse(visited.containsKey(state));
        visited.put(state, true);
        assertTrue(visited.containsKey(state));
    }

    @Test
    void largeNumberOfEntries_allRetrievable() {
        HashTable<Integer, Integer> big = new HashTable<>();
        for (int i = 0; i < 200; i++) big.put(i, i * 10);
        for (int i = 0; i < 200; i++) {
            assertEquals(i * 10, big.get(i));
        }
        assertEquals(200, big.size());
    }
}