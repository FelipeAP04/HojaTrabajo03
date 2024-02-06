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

    // Gnome Sort
    private static void gnomeSort(List<Element> elements) {
        int i = 1;
        while (i < elements.size()) {
            if (i == 0 || elements.get(i).number >= elements.get(i - 1).number) {
                i++;
            } else {
                Collections.swap(elements, i, i - 1);
                if (i > 1) {
                    i--;
                }
            }
        }
    }

    // Merge Sort
    private static void mergeSort(List<Element> elements) {
        if (elements.size() <= 1) {
            return;
        }
        int mid = elements.size() / 2;
        List<Element> left = new ArrayList<>(elements.subList(0, mid));
        List<Element> right = new ArrayList<>(elements.subList(mid, elements.size()));

        mergeSort(left);
        mergeSort(right);

        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).number < right.get(j).number) {
                elements.set(k++, left.get(i++));
            } else {
                elements.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            elements.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            elements.set(k++, right.get(j++));
        }
    }
    
    //Implementación de Quick Sort
    private static void quickSort(List<Element> elements, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(elements, low, high);
            quickSort(elements, low, partitionIndex - 1);
            quickSort(elements, partitionIndex + 1, high);
        }
    }

    private static int partition(List<Element> elements, int low, int high) {
        int pivot = elements.get(high).number;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (elements.get(j).number <= pivot) {
                i++;
                Collections.swap(elements, i, j);
            }
        }

        Collections.swap(elements, i + 1, high);
        return i + 1;
    }
}