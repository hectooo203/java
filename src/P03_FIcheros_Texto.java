import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class P03_FIcheros_Texto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path rutaArchivo = Path.of("src", "studentsDAM2.txt");
        String usuarioCorrecto = "TWO";

        if (!Files.exists(rutaArchivo)) {
            System.err.println("No existe el archivo en: " + rutaArchivo.toAbsolutePath());
            scanner.close();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo.toFile()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Problema de lectura E/S: " + e.getLocalizedMessage());
        }

        System.out.println("Para poder escribir debes indicar el usuario correcto: ");
        String usuario = scanner.nextLine();

        if (usuario.equals(usuarioCorrecto)) {
            System.out.println("¿Qué nombre quieres escribir?: ");
            String nuevoNombre = scanner.nextLine();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo.toFile(), true))) {
                bw.newLine();
                bw.write(nuevoNombre);
                System.out.println("Nombre guardado correctamente.");
            } catch (IOException e) {
                System.err.println("Problema de escritura E/S: " + e.getLocalizedMessage());
            }
        } else {
            System.out.println("Usuario incorrecto. No se ha escrito nada.");
        }

        scanner.close();
    }
}