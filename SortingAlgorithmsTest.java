/**
 * Clase para pruebas JUnit
 * Esta clase verifica que cada algoritmo ordene de manera correcta las listas, para eso se les da una lista desordenada a cada una.
 */
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class SortingAlgorithmsTest {
    @Test
    public void testBubbleSort() {
        List<Element> elements = new ArrayList<>(Arrays.asList(
            new Element(0, 5),
            new Element(1, 3),
            new Element(2, 8),
            new Element(3, 4),
            new Element(4, 1)
        ));
        SortingAlgorithms.bubbleSort(elements);
        for (int i = 1; i < elements.size(); i++){
            assertTrue(elements.get(i - 1).number <= elements.get(i).number);
        }
    }

    @Test
    public void testGnomeSort() {
        List<Element> elements = new ArrayList<>(Arrays.asList(
            new Element(0, 5),
            new Element(1, 3),
            new Element(2, 8),
            new Element(3, 4),
            new Element(4, 1)
        ));
        SortingAlgorithms.gnomeSort(elements);
        for (int i = 1; i < elements.size(); i++){
            assertTrue(elements.get(i - 1).number <= elements.get(i).number);
        }
    }

    @Test
    public void testMergeSort() {
        List<Element> elements = new ArrayList<>(Arrays.asList(
            new Element(0, 5),
            new Element(1, 3),
            new Element(2, 8),
            new Element(3, 4),
            new Element(4, 1)
        ));
        SortingAlgorithms.mergeSort(elements);
        for (int i = 1; i < elements.size(); i++){
            assertTrue(elements.get(i - 1).number <= elements.get(i).number);
        }
    }

    @Test
    public void testQuickSort() {
        List<Element> elements = new ArrayList<>(Arrays.asList(
            new Element(0, 5),
            new Element(1, 3),
            new Element(2, 8),
            new Element(3, 4),
            new Element(4, 1)
        ));
        SortingAlgorithms.quickSort(elements, 0, elements.size() - 1);
        for (int i = 1; i < elements.size(); i++){
            assertTrue(elements.get(i - 1).number <= elements.get(i).number);
        }
    }

    @Test
    public void testRadixSort() {
        List<Element> elements = new ArrayList<>(Arrays.asList(
            new Element(0, 5),
            new Element(1, 3),
            new Element(2, 8),
            new Element(3, 4),
            new Element(4, 1)
        ));
        SortingAlgorithms.radixSort(elements);
        for (int i = 1; i < elements.size(); i++){
            assertTrue(elements.get(i - 1).number <= elements.get(i).number);
        }
    }
}
