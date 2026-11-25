
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Acceso_Datos_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[ ][ ] alumnos = {{"Ana", "García López", "8.5"}, 
                                {"Carlos", "Pérez Gómez", "6.2"},
                                {"Elena", "Martínez Ruiz", "9.0"},
                                {"David", "Sánchez Fernández", "4.7"},
                                {"Lucía", "Díaz Romero", "7.8"}
                                };  
        File csv = null;
        String ruta = "";
        Path rutaCompleta = null;
        boolean sobreescribir = false;
        

        try {

            while (sobreescribir != true) { 
                System.out.println("Como quieres que se llame el fichero csv: ");
                csv = new File(scanner.nextLine());

                System.out.println("Dime donde quieres guardar el fichero csv: ");
                ruta = scanner.nextLine();

                rutaCompleta = Path.of(ruta, csv.toString());

                if (Files.exists(rutaCompleta)) {
                    System.out.println("La ruta existe...");
                    System.out.println("Desea sobreescribir el archivo? [s/n]");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        sobreescribir = true;
                    } else {
                        System.out.println("Elige otro nombre o ruta distinta.\n");
                    }
                    if (Files.isReadable(rutaCompleta)) {
                        System.out.println("Se puede escribir en la ruta...");
                    }
                } else {
                    System.out.println("El archivo no existe, se creara uno nuevo.");
                    sobreescribir = true;
                }
            }

            System.out.println("El archivo se va a guardar en " + rutaCompleta);

        } catch (Exception e) {
            System.err.println("Error de E/S: " + e.getLocalizedMessage());
        } finally {
            scanner.close();
        }
    
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaCompleta.toString()))) {
            
            System.out.print("Escribiendo archivo...");

            for (String[] dato : alumnos) {

                for (int i = 0; i < 3; i++) {
                    escritor.write(dato[i]);
                    if (i < dato.length - 1) {
                        escritor.write(",");
                    }
                }
                escritor.newLine();
            }

        } catch (Exception e) {
            System.err.println("Error en la escritura del archivo: " + e.getLocalizedMessage());
        }
    }
}
