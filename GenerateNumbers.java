import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateNumbers {
    public static void main(String[] args) {
        // Generar lista de 3000 números enteros
        int size = 3000;
        int[] numbers = generateNumbers(size);

        // Guardar la lista en un archivo
        saveNumbersToFile(numbers, "numeros.txt");

        System.out.println("Lista de números generada y guardada en 'numeros.txt'");
    }

    private static int[] generateNumbers(int size) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = (int) (Math.random() * 1000); // Números aleatorios entre 0 y 999
        }
        return numbers;
    }

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
