import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Acceso_Datos_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero_lineas = 0;
        int numero_mostrar = 0;
        String carpeta_destino = "Archivos txt de prueba/";
        String archivo_secuencia = "";
        Path rutaCompleta = null;

        try {
            System.out.print("Dime el numero de lineas que quieres mostrar: ");
            numero_lineas = scanner.nextInt();

            System.out.print("Dime el numero que quieres mostrar: ");
            numero_mostrar = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Como quieres que se llame el archivo para guardar la secuencia: ");
            archivo_secuencia += scanner.nextLine();

            if (!archivo_secuencia.endsWith(".txt")) {
                archivo_secuencia += ".txt";
            }

            rutaCompleta = Path.of(carpeta_destino, archivo_secuencia);

        } catch (InputMismatchException e) {
            System.err.println("Error de lectura");
        }catch (Exception e) {
            System.err.println("A ocurrido un error: "+ e.getLocalizedMessage());
        } 

        try (BufferedWriter br = new BufferedWriter(new FileWriter(rutaCompleta.toString()))) {
            for (int i = 0; i < (numero_lineas+1); i++) {
                for (int f = 0; f < i; f++) {
                    br.write(String.valueOf(numero_mostrar));
                }
                br.newLine();
            }
        } catch (Exception e) {
            
        }

        
    }
}