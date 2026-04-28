package integradora.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest
{

    private BinarySearchTree<Integer> stage11; // Árbol binario con partidas registradas

    @BeforeEach
    void setupStage11()
    {
        stage11 = new BinarySearchTree<>();
        stage11.insert(50);
        stage11.insert(30);
        stage11.insert(70);
        stage11.insert(20);
        stage11.insert(40);
    }

    // Objetivo: Validar el árbol binario de partidas jugadas


    @Test
    void insert_newPlayer_nodeInsertedCorrectly()
    {
        // Arrange
        int newValue = 60;

        // Act
        stage11.insert(newValue);

        // Assert
        assertNotNull(stage11.search(newValue), "Inserted node should be found in the tree");
        assertEquals(6, stage11.size());
    }

    @Test
    void search_existingUsername_returnsPlayerFound()
    {
        // Arrange
        int target = 30;

        // Act
        Integer result = stage11.search(target);

        // Assert
        assertNotNull(result, "Search should return the found element");
        assertEquals(30, result);
    }

    @Test
    void search_nonExistingValue_returnsNull()
    {
        // Arrange
        int target = 99;

        // Act
        Integer result = stage11.search(target);

        // Assert
        assertNull(result, "Search should return null for a non-existing value");
    }

    @Test
    void inOrder_afterInserts_printsPlayersInOrder()
    {
        // Arrange — tree has 50, 30, 70, 20, 40
        // inOrder should print: 20, 30, 40, 50, 70

        // Act & Assert
        assertDoesNotThrow(() -> stage11.inOrder(),
                "inOrder traversal should execute without errors");
    }

    @Test
    void delete_existingNode_nodeRemovedCorrectly()
    {
        // Arrange
        int target = 30;

        // Act
        stage11.delete(target);

        // Assert
        assertNull(stage11.search(target), "Deleted node should not be found");
        assertEquals(4, stage11.size());
    }

    @Test
    void delete_nonExistingNode_treeUnchanged()
    {
        // Arrange
        int sizeBefore = stage11.size();

        // Act
        stage11.delete(99);

        // Assert
        assertEquals(sizeBefore, stage11.size(), "Size should not change when deleting non-existing node");
    }

    @Test
    void isEmpty_afterInserts_returnsFalse()
    {
        // Arrange — stage11 already has elements

        // Act
        boolean empty = stage11.isEmpty();

        // Assert
        assertFalse(empty, "isEmpty should return false when tree has elements");
    }

    @Test
    void isEmpty_emptyTree_returnsTrue()
    {
        // Arrange
        BinarySearchTree<Integer> emptyTree = new BinarySearchTree<>();

        // Act
        boolean empty = emptyTree.isEmpty();

        // Assert
        assertTrue(empty, "isEmpty should return true for an empty tree");
    }

    @Test
    void size_afterInserts_returnsCorrectSize()
    {
        // Arrange — stage11 has 5 elements

        // Act
        int size = stage11.size();

        // Assert
        assertEquals(5, size, "Size should return the correct number of nodes");
    }

    @Test
    void height_afterInserts_returnsCorrectHeight()
    {
        // Arrange — tree: 50 -> 30,70 -> 20,40
        // height should be 3

        // Act
        int height = stage11.height();

        // Assert
        assertEquals(3, height, "Height should be 3 for this tree structure");
    }

    @Test
    void findMax_afterInserts_returnsLargestValue()
    {
        // Arrange — max value is 70

        // Act
        Integer max = stage11.findMax();

        // Assert
        assertEquals(70, max, "findMax should return the largest value in the tree");
    }
}