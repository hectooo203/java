import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Acceso_Datos_2 {
    public static void main(String[] args) {
        String ruta=".";

        try {
            if(args.length>=1) ruta=args[0];
            File fich=new File(ruta);

            if (!fich.exists()) {
                throw new Exception("La ruta no existe: "+ ruta);
            }
            if (!fich.canRead()) {
                throw new Exception("No se puede acceder al fichero o directorio: "+ fich);
            }

            long milisegundos = fich.lastModified();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = sdf.format(new Date(milisegundos));

            if (!fich.isFile()) {
                System.out.print("/ ");

                System.out.print(fich.canRead() ? "r" : "-");
                System.out.print(fich.canWrite() ? "w" : "-");
                System.out.print(fich.canExecute() ? "x " : "- ");
                System.out.print(fich.length() + " ");
                System.out.print(fechaFormateada+" ");
                System.out.println(fich.getName());

                File[] ficheros = fich.listFiles();

                for (File f : ficheros) {

                    long milisegundos_f = f.lastModified();
                    String fechaElemento = sdf.format(new Date(milisegundos_f));

                    System.out.print(f.isFile() ? "_ " : "/ ");
                    System.out.print(f.canRead() ? "r" : "-");
                    System.out.print(f.canWrite() ? "w" : "-");
                    System.out.print(f.canExecute() ? "x " : "- ");
                    System.out.print(f.length() + " ");
                    System.out.print(fechaFormateada+" ");
                    System.out.println(f.getName());
                }

            } else {

                System.out.print("_ ");
                System.out.print(fich.canRead() ? "r" : "-");
                System.out.print(fich.canWrite() ? "w" : "-");
                System.out.print(fich.canExecute() ? "x " : "- ");
                System.out.print(fich.length() + " ");
                System.out.print(fechaFormateada+" ");
                System.out.println(fich.getName());

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
