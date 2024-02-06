import java.io.*;
import java.util.*;

class Element {
    int position;
    int number;

    public Element(int position, int number) {
        this.position = position;
        this.number = number;
    }
}

public class SortingAlgorithms {
    public static void main(String[] args) {
        // Cargar datos desde el archivo externo
        List<Element> elements = loadDataFromFile("numeros.txt");

        // Algoritmo Gnome Sort
        gnomeSort(elements);
        printResults(elements, "Gnome Sort");

        // Algoritmo Merge Sort
        mergeSort(elements);
        printResults(elements, "Merge Sort");

        // Algoritmo Quick Sort
        quickSort(elements, 0, elements.size() - 1);
        printResults(elements, "Quick Sort");

        // Algoritmo Radix Sort
        radixSort(elements);
        printResults(elements, "Radix Sort");

        // Algoritmo Bubble Sort
        bubbleSort(elements);
        printResults(elements, "Bubble Sort");
    }

    // Método para cargar datos desde un archivo externo
    private static List<Element> loadDataFromFile(String filename) {
        List<Element> elements = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\s+");
                int position = Integer.parseInt(data[0]);
                int number = Integer.parseInt(data[1]);
                elements.add(new Element(position, number));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return elements;
    }
}