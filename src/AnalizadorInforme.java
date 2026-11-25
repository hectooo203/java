import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalizadorInforme {
    public static void main(String[] args) throws Exception {
        String ruta = ".";
        String ficheroSalida = null;

        if (args.length >= 2) {
            ficheroSalida = args[1];
        } else {
            System.out.println("Porfavor proporcione un fichero y un archivo donde realizar el informe");
            return;
        }

        try {
            File fich = new File(ruta);

            if (!fich.exists()) {
                throw new Exception("La ruta no existe: " + ruta);
            }
            if (!fich.canRead()) {
                throw new Exception("No se puede acceder al fichero o directorio: " + fich);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            File[] ficheros;

            if (!fich.isFile()) {

                long milisegundos = fich.lastModified();
                String fechaFormateada = sdf.format(new Date(milisegundos));

                System.out.print("/ ");
                System.out.print(fich.canRead() ? "r" : "-");
                System.out.print(fich.canWrite() ? "w" : "-");
                System.out.print(fich.canExecute() ? "x " : "- ");
                System.out.print(fich.length() + " ");
                System.out.print(fechaFormateada + " ");
                System.out.println(fich.getName());

                ficheros = fich.listFiles();
                if (ficheros == null) {
                    throw new Exception("No se pudo obtener el contenido del directorio.");
                }

                for (File f : ficheros) {
                    long milisegundos_f = f.lastModified();
                    String fechaElemento = sdf.format(new Date(milisegundos_f));

                    System.out.print(f.isFile() ? "_ " : "/ ");
                    System.out.print(f.canRead() ? "r" : "-");
                    System.out.print(f.canWrite() ? "w" : "-");
                    System.out.print(f.canExecute() ? "x " : "- ");
                    System.out.print(f.length() + " ");
                    System.out.print(fechaElemento + " ");
                    System.out.println(f.getName());
                }

            } else {
                long milisegundos = fich.lastModified();
                String fechaFormateada = sdf.format(new Date(milisegundos));

                System.out.print("_ ");
                System.out.print(fich.canRead() ? "r" : "-");
                System.out.print(fich.canWrite() ? "w" : "-");
                System.out.print(fich.canExecute() ? "x " : "- ");
                System.out.print(fich.length() + " ");
                System.out.print(fechaFormateada + " ");
                System.out.println(fich.getName());

                ficheros = new File[]{fich};
            }


            if (ficheroSalida != null) {

                try (PrintWriter writer = new PrintWriter(new FileWriter(ficheroSalida))) {

                    writer.println("==================================================");
                    writer.println("INFORME DE DIRECTORIO");
                    writer.println("Fecha de generación: " + sdf.format(new Date()));
                    writer.println("Ruta analizada: " + fich.getAbsolutePath());
                    writer.println("==================================================");
                    writer.println();


                    for (File f : ficheros) {
                        String tipo = f.isFile() ? "_ " : "/ ";
                        String permisos = (f.canRead() ? "r" : "-") +
                                (f.canWrite() ? "w" : "-") +
                                (f.canExecute() ? "x " : "- ");
                        String fechaElemento = sdf.format(new Date(f.lastModified()));

                        writer.print(tipo);
                        writer.print(permisos);
                        writer.print(f.length() + " ");
                        writer.print(fechaElemento + " ");
                        writer.println(f.getName());
                    }

                    System.out.println("\nInforme guardado en: " + ficheroSalida);

                    System.out.println("Leyendo archivo solo con permisos de lectura...");

                    try (BufferedReader leerarchivo = new BufferedReader(new FileReader(ficheroSalida))) {

                        System.out.println("-------------------------------------------------");
                        System.out.println("  ARCHIVOS DE SOLO ESCRITURA, TAMAÑO MAYOR A 0  ");
                        System.out.println("-------------------------------------------------");

                        String linea;

                        while ((linea = leerarchivo.readLine()) != null) {

                            String[] cadenaseparada = linea.split(" ");

                            if (cadenaseparada.length >= 3) {
                                try {
                                    Long tamanoBytes = Long.parseLong(cadenaseparada[2]);

                                    if (cadenaseparada[1].equals("-w-") && tamanoBytes > 0) {
                                        System.out.println(linea);
                                    }
                                } catch (NumberFormatException e) {
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {

                    }

                } catch (IOException e) {
                    System.err.println("Error al escribir el archivo (" + ficheroSalida + "): " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}