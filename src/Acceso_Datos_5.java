import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Acceso_Datos_5 {
    public static void main(String[] args) {

        //Comprobar que la cantidad de argumentos es la correcta
        if (args.length < 2 || args.length > 3) {
            System.err.println("Error: Debes indicar el archivo y el texto a buscar.");
            System.err.println("Uso: java Acceso_Datos_5 <ruta_archivo> <texto_a_buscar> [-i]");
            return;
        }

        File fichero = new File(args[0]);
        String textoaBuscar = args[1];

        //Comprobar que el archivo existe, sino existe mostrar mensaje de error y finalizar programa
        if (!fichero.exists() || !fichero.isFile()) {
            System.err.println("Error: El fichero '" + args[0] + "' no existe o no es un archivo válido.");
            return;
        }

        System.out.println("El fichero existe, buscando '" + textoaBuscar + "'...\n");

        //Recorrer el fichero y buscar la palabra solicitada
        try (BufferedReader leerArchivo = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int i = 1;

            while ((linea = leerArchivo.readLine()) != null) {
                if (args.length == 3 && args[2].equalsIgnoreCase("-i")) {
                    if (linea.toLowerCase().contains(textoaBuscar.toLowerCase())) {
                        System.out.println(i+": "+linea);
                    }
                } else {
                    if (linea.contains(textoaBuscar)) {
                        System.out.println(i+": "+linea);
                    }
                }
                i++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}