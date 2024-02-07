/**
 * Clase GenerateNumbers
 * Felipe Aguilar - 23195
 * Fernando Rueda - 23748
 * Clase principal que genera una lista de números enteros aleatorios y utiliza métodos extáticos para generar los números y guardarlos.
 * Fecha de creación: 06/02/2024
 * Fecha de última modificación: 06/02/2024
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateNumbers {
    /**
     * Punto de entrada principal del programa.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        // Generar lista de 3000 números enteros
        int size = 3000;
        int[] numbers = generateNumbers(size);

        // Guardar la lista en un archivo
        saveNumbersToFile(numbers, "numeros.txt");

        System.out.println("Lista de números generada y guardada en 'numeros.txt'");
    }

    /**
     * Genera un arreglo de números enteros aleatorios.
     * 
     * @param size El tamaño del arreglo a generar.
     * @return Un arreglo de números enteros aleatorios.
     */
    private static int[] generateNumbers(int size) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = (int) (Math.random() * 1000); // Números aleatorios entre 0 y 999
        }
        return numbers;
    }

    /**
     * Guarda un arreglo de números enteros en un archivo de texto.
     * 
     * @param numbers Arreglo de números enteros a guardar.
     * @param filename Nombre del archiivo donde se guardan los números.
     */
    private static void saveNumbersToFile(int[] numbers, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < numbers.length; i++) {
                // Guardar número y su posición en el archivo
                writer.write(i + " " + numbers[i]);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
