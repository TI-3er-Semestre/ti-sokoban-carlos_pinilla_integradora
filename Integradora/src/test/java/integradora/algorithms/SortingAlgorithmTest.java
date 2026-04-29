package integradora.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmTest
{

    private int[] stage13; // Lista desordenada de rankings para validar ordenamiento

    @BeforeEach
    void setupStage13()
    {
        stage13 = new int[]{5, 2, 8, 1, 9, 3};
    }


    // Objetivo: Validar algoritmos de ordenamiento


    @Test
    void bubbleSort_unsortedArray_sortedAscending()
    {
        // Arrange
        int[] arr = {5, 2, 8, 1};

        // Act
        SortingAlgorithm.bubbleSort(arr);

        // Assert
        assertArrayEquals(new int[]{1, 2, 5, 8}, arr,
                "Array should be sorted ascending after bubbleSort");
    }

    @Test
    void bubbleSort_anotherArray_sortedCorrectly()
    {
        // Arrange
        int[] arr = {10, 7, 3};

        // Act
        SortingAlgorithm.bubbleSort(arr);

        // Assert
        assertArrayEquals(new int[]{3, 7, 10}, arr,
                "Elements should be sorted correctly");
    }

    @Test
    void selectionSort_unsortedArray_sortedAscending()
    {
        // Arrange
        int[] arr = stage13.clone();

        // Act
        SortingAlgorithm.selectionSort(arr);

        // Assert
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, arr,
                "Array should be sorted ascending after selectionSort");
    }

    @Test
    void insertionSort_unsortedArray_sortedAscending()
    {
        // Arrange
        int[] arr = stage13.clone();

        // Act
        SortingAlgorithm.insertionSort(arr);

        // Assert
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, arr,
                "Array should be sorted ascending after insertionSort");
    }

    @Test
    void bubbleSort_alreadySortedArray_remainsSorted()
    {
        // Arrange
        int[] arr = {1, 2, 3, 4, 5};

        // Act
        SortingAlgorithm.bubbleSort(arr);

        // Assert
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr,
                "Already sorted array should remain sorted");
    }

    @Test
    void selectionSort_singleElement_noChange()
    {
        // Arrange
        int[] arr = {42};

        // Act
        SortingAlgorithm.selectionSort(arr);

        // Assert
        assertArrayEquals(new int[]{42}, arr,
                "Single element array should not change");
    }
}