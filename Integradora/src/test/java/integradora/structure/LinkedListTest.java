package integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest
{

    private LinkedList<String> stage10; // Lista enlazada vacía para validar inserción y eliminación

    @BeforeEach
    void setupStage10()
    {
        stage10 = new LinkedList<>();
    }


    // Objetivo: Validar operaciones de la lista enlazada


    @Test
    void add_newNode_nodeAddedToList()
    {
        // Arrange
        String data = "node1";

        // Act
        stage10.add(data);

        // Assert
        assertEquals(data, stage10.get(0), "Node should be added to the list");
        assertEquals(1, stage10.size());
    }

    @Test
    void remove_existingNode_nodeRemovedCorrectly()
    {
        // Arrange
        stage10.add("node1");
        stage10.add("node2");
        stage10.add("node3");

        // Act
        boolean removed = stage10.remove("node2");

        // Assert
        assertTrue(removed, "Node should be removed correctly");
        assertEquals(2, stage10.size());
        assertFalse(stage10.contains("node2"));
    }

    @Test
    void get_indexZero_returnsElementAtPosition()
    {
        // Arrange
        stage10.add("first");
        stage10.add("second");
        stage10.add("third");

        // Act
        String result = stage10.get(0);

        // Assert
        assertEquals("first", result, "Should return the element at the given position");
    }

    @Test
    void size_afterInserts_returnsCorrectSize()
    {
        // Arrange
        stage10.add("a");
        stage10.add("b");
        stage10.add("c");

        // Act
        int result = stage10.size();

        // Assert
        assertEquals(3, result, "Should return the correct size of the list");
    }

    @Test
    void isEmpty_emptyList_returnsTrue()
    {
        // Arrange — stage10 is already empty

        // Act
        boolean empty = stage10.isEmpty();

        // Assert
        assertTrue(empty, "isEmpty should return true when list is empty");
    }

    @Test
    void isEmpty_afterAdd_returnsFalse()
    {
        // Arrange
        stage10.add("node1");

        // Act
        boolean empty = stage10.isEmpty();

        // Assert
        assertFalse(empty, "isEmpty should return false after adding an element");
    }

    @Test
    void contains_existingElement_returnsTrue()
    {
        // Arrange
        stage10.add("node1");
        stage10.add("node2");

        // Act
        boolean contains = stage10.contains("node1");

        // Assert
        assertTrue(contains, "contains should return true for an existing element");
    }

    @Test
    void contains_nonExistingElement_returnsFalse()
    {
        // Arrange
        stage10.add("node1");

        // Act
        boolean contains = stage10.contains("node99");

        // Assert
        assertFalse(contains, "contains should return false for a non-existing element");
    }

    @Test
    void addFirst_newNode_nodeAddedAtBeginning()
    {
        // Arrange
        stage10.add("second");
        stage10.add("third");

        // Act
        stage10.addFirst("first");

        // Assert
        assertEquals("first", stage10.get(0), "addFirst should add the node at the beginning");
        assertEquals(3, stage10.size());
    }

    @Test
    void removeFirst_afterInserts_returnsAndRemovesFirstElement()
    {
        // Arrange
        stage10.add("first");
        stage10.add("second");

        // Act
        String result = stage10.removeFirst();

        // Assert
        assertEquals("first", result, "removeFirst should return and remove the first element");
        assertEquals(1, stage10.size());
    }

    @Test
    void clear_afterInserts_listBecomesEmpty()
    {
        // Arrange
        stage10.add("a");
        stage10.add("b");
        stage10.add("c");

        // Act
        stage10.clear();

        // Assert
        assertTrue(stage10.isEmpty(), "List should be empty after clear");
        assertEquals(0, stage10.size());
    }

    @Test
    void get_outOfBoundsIndex_throwsException()
    {
        // Arrange
        stage10.add("only");

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> stage10.get(5),
                "Should throw IndexOutOfBoundsException for invalid index");
    }
}
