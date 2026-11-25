import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Acceso_Datos_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File csv = new File("");
        File html = new File("");
        String ruta = "";
        Path rutaCompleta = null;
        Boolean encabezados;

        try {
            System.out.println("Dime donde se encuentra el fichero csv: ");
            ruta = scanner.nextLine();

            System.out.println("Dime como se llama el archivo: ");
            csv = new File(scanner.nextLine());

            rutaCompleta = Path.of(ruta, csv.toString());

            if (Files.exists(rutaCompleta)) {
                System.out.println("La ruta existe...");
                if (Files.isReadable(rutaCompleta)) {
                    System.out.println("El archivo se puede leer...");
                } else {
                    System.out.println("El archivo no se puede leer...");
                }
            } else {
                System.out.println("La ruta no existe...");
                System.out.print("Asegurate de que la ruta exista");
            }

        } catch (Exception e) {
            System.err.println("Ocurrio un error de E/S: " + e.getLocalizedMessage());
        }

        System.out.println("El archivo tiene encabezados? [s/n]");
        encabezados = (scanner.nextLine().equals("s")) ? true : false;

        System.out.println("Como quieres que se llame el archivo HTML");
        html = new File(scanner.nextLine());

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaCompleta.toFile()));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(html))) {
            
                String lineas = "";
                String[] datos;
                String secuenciaHTML = "";
                
                while ((lineas = lector.readLine()) != null ) {
                    datos = lineas.split(",");
                    if (encabezados) {
                        secuenciaHTML += "<table> \n <thead> \n <tr> \n ";
                        for (String dato : datos) {
                            secuenciaHTML += "<th>" + dato + "</th> \n";
                        }
                        secuenciaHTML += "</tr> \n </thead> \n ";
                    } else {
                        secuenciaHTML += "<tbody> \n <tr> \n";
                        for (String dato : datos) {
                            secuenciaHTML += "<td>" + dato + "</td> \n";
                        }
                        secuenciaHTML += "</tbody> \n </tr> \n </table>";
                    }
                }

                String encabezadoHTML = "<!DOCTYPE html>\n" +
                                            "<html lang=\"es\">\n" +
                                            "<head>\n" +
                                            "    <meta charset=\"UTF-8\">\n" +
                                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                            "    <title>Título de tu página</title>\n" +
                                            "</head>\n" +
                                            "<body>\n" +
                                                secuenciaHTML +
                                            "</body>\n" +
                                            "</html>";
                
                escritor.write(encabezadoHTML);


        } catch (IOException e) {
            System.err.println("Ocurrio un error de E/S: " + e.getLocalizedMessage());
        } finally {
            scanner.close();
        }
    }
}