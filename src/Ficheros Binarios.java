import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

//Actividad 1

public class Actividad1_CopiarBinario {
    public static void main(String[] args) {
        Path origen = Path.of("archivos/imagen.png");
        Path destino = Path.of("archivos/imagen_copia.png");

        if (!Files.exists(origen)) {
            System.err.println("El archivo de origen no existe: " + origen.toAbsolutePath());
            return;
        }

        byte[] buffer = new byte[8192];
        int bytesLeidos;

        try (FileInputStream fis = new FileInputStream(origen.toFile());
             FileOutputStream fos = new FileOutputStream(destino.toFile())) {

            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);
            }

            System.out.println("Imagen copiada en: " + destino.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error de E/S al copiar el archivo: " + e.getMessage());
        }
    }
}

//Actividad 2

public class Actividad2_TextoABinario {
    public static void main(String[] args) {
        Path archivoTexto = Path.of("archivos/mensaje.txt");
        Path archivoBinario = Path.of("archivos/mensaje.bin");

        // 1. Escribir texto en el archivo .txt
        try (FileWriter fw = new FileWriter(archivoTexto.toFile(), StandardCharsets.UTF_8)) {
            fw.write("Hola, este es un archivo de texto.\nSegunda linea de prueba.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de texto: " + e.getMessage());
            return;
        }

        // 2. Leer el archivo de texto como bytes y escribirlo en binario
        byte[] buffer = new byte[1024];
        int bytesLeidos;

        try (FileInputStream fis = new FileInputStream(archivoTexto.toFile());
             FileOutputStream fos = new FileOutputStream(archivoBinario.toFile())) {

            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);
            }

        } catch (IOException e) {
            System.err.println("Error al convertir a binario: " + e.getMessage());
            return;
        }

        // 3. Leer el archivo binario creado
        try (FileInputStream fisBin = new FileInputStream(archivoBinario.toFile())) {
            byte[] contenidoBinario = fisBin.readAllBytes();

            System.out.println("Total de bytes leídos: " + contenidoBinario.length);
            System.out.print("Bytes: ");
            for (byte b : contenidoBinario) {
                System.out.print(b + " ");
            }
            System.out.println();

            String textoRecuperado = new String(contenidoBinario, StandardCharsets.UTF_8);
            System.out.println("Texto convertido desde binario:\n" + textoRecuperado);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo binario: " + e.getMessage());
        }
    }
}
