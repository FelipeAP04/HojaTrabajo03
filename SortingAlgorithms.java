/**
 * Clase Sorting Algorithms
 * Felipe Aguilar - 23195
 * Fernando Rueda - 23748
 * Clase que implementa varios algoritmos de ordenamiento.
 * Fecha de creación: 06/02/2024
 * Fecha de última modificación: 06/02/2024
 */
import java.io.*;
import java.util.*;

import javax.lang.model.util.Elements;

/**
 * Representa un elemento con una posición y un número, utilizado para demostrar diferentes algoritmos de ordenamiento.
 * 
 */
class Element {
    int position;
    int number;

    /**
     * Constructor para crear un nuevo elemento.
     * 
     * @param position La posición del elemento.
     * @param number El número asociado al elemento.
     */
    public Element(int position, int number) {
        this.position = position;
        this.number = number;
    }
}

public class SortingAlgorithms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Presione cualquier tecla para comenzar");
        String start = scanner.nextLine();

        List<Element> elements = new ArrayList<>();
        final int totalNumbers = 3000;
        final int blockSize = 11;

        // Leer bloques de números hasta alcanzar un total de 3000 números
        for (int i = 0; i < totalNumbers; i += blockSize) {
            int remaining = totalNumbers - i;
            int blockToRead = Math.min(blockSize, remaining);


        // Cargar datos desde el archivo externo
        List<Element> blockElements = loadDataFromFile("numeros.txt", i, blockToRead);

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

        elements.addAll(blockElements);
        }
    }

    /** 
     * Método para cargar datos desde un archivo externo.
     * 
     * @param filename El nombre del archivo desde donde se cargarán losd datos.
     * @return Una lista de {@link Element} con los dtos cargados desde el archivo. 
     * @throws FileNotFoundException Si el archivo especificado no existe.
    */
    private static List<Element> loadDataFromFile(String filename, int startIndex, int blockSize) {
        List<Element> elements = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            for (int i = 0; i < startIndex; i++) {
                scanner.nextLine();
            }

            // Leer el bloque de números
            for (int i = 0; i < blockSize && scanner.hasNextLine(); i++) {
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

    /**
     * Método para imprimir los resultados después de cada algoritmo.
     * 
     * @param elements Lista de {@link Elements} que contiene los elementos ordenados.
     * @param algorithmName Nombre del algoritmo de ordenamiento que se ha aplicado a los elementos.
    */
    private static void printResults(List<Element> elements, String algorithmName) {
        System.out.println("\n" + algorithmName + " Results:");
        for (Element element : elements) {
            System.out.println("Position: " + element.position + ", Number: " + element.number);
        }
    }

    /**
     * Ordena una lista de elementos utilizando el algorimto Gnome Sort.
     * 
     * @param elements Lista de elementos a ordenar.
     */
    public static void gnomeSort(List<Element> elements) {
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

    /**
     * Ordena una lista de elementos utilizando el algoritmo Merge Sort.
     * 
     * @param elements Lista de elementos a ordenar.
     */
    public static void mergeSort(List<Element> elements) {
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

    /**
     * Ordena un alista de elementos utilizando el algoritmo Quick Sort.
     * 
     * @param elements Lista de elementos a ordenar.
     * @param low Índice inicial de la sección de la lista a ordenar.
     * @param high Índice final de la sección de la lista a ordenar.
     */
    public static void quickSort(List<Element> elements, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(elements, low, high);
            quickSort(elements, low, partitionIndex - 1);
            quickSort(elements, partitionIndex + 1, high);
        }
    }

    /**
     * Realiza la partición de una lista de elementos para el algorirmo de Quick Sort.
     * @param elements La lista de elementos {@link Element} a particionar.
     * @param low El índice inicial desde donde se debe comenzar a particionar.
     * @param high El índice final hasta donde se debe particionar.
     * @return El índice del nuevo pivote después de la partición.
     */
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

    /**
     * Ordena una lista de elementos utilizando el algorimto Radix Sort.
     * 
     * @param elements Lista de elementos a ordenar.
     */
    public static void radixSort(List<Element> elements) {
        final int K = 10; // Número de cubos por ronda
        int D = (int) Math.ceil(Math.log(elements.stream().mapToInt(e -> e.number + 1).max().orElse(1)) / Math.log(K));
        
        List<Element> tempArray = new ArrayList<>(elements);

        for (int d = 0; d < D; d++) {
            Collections.fill(tempArray, null);
            kSortCopy(elements, tempArray, K, d);
            
            elements.clear();
            elements.addAll(tempArray);
        }
    }

    /**
     * Realiza una pasada del ordenamiento Radix Sort en la lista de elementos.
     * 
     * @param elements La lista original de elementos {@link Element} a ordenar.
     * @param tempArray Una lista temporal usada para almacenar los elementos ordenados en esta pasada.
     * @param K El número de cubetas (buckets) utilizado para el ordenamiento, que corresponde al rango de valores posibles para cada dígito.
     * @param d La cigra significativa actual por la cual se está ordenando, empezando por 0 para el dígito menos significativo.
     */
    private static void kSortCopy(List<Element> elements, List<Element> tempArray, int K, int d) {
        int[] count = new int[K];
        int n = elements.size();

        for (int i = 0; i < n; i++) {
            count[key(elements.get(i).number, K, d)]++;
        }

        int sum = 0;
        for (int k = 0; k < K; k++) {
            int next = sum + count[k];
            count[k] = sum;
            sum = next;
        }

        for (int i = 0; i < n; i++) {
            tempArray.set(count[key(elements.get(i).number, K, d)]++, elements.get(i));
        }

        elements.clear();
        elements.addAll(tempArray);
    }

    /**
     * Calcula la clave (key) para el ordenamiento Radix basándose en la cifra significativa 'd' de un número.
     * @param number El número del cual se calcula la clave.
     * @param K El núero de cubetas (buckets) utilizado en el ordenamiento, que corresponde al rango de valores posibles para cada dígito.
     * @param d La cigra significativa actual por la cual se está calculando la llave.
     * @return La clave calculada, que indica la cubeta correspondiente para el número dado y la cifra significativa 'd'
     */
    private static int key(int number, int K, int d) {
        return (number / (int) Math.pow(K, d)) % K;
    }

    /**
     * Ordena una lista de elementos utilizando el algoritmo Bubble Sort.
     * 
     * @param elements Lista de elementos a ordenar.
     */
    public static void bubbleSort(List<Element> elements) {
        int n = elements.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (elements.get(j).number > elements.get(j + 1).number) {
                    Collections.swap(elements, j, j + 1);
                }
            }
        }
    }
}
