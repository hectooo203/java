

public class Acceso_Datos_1 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No se pasaron parámetros.");
            return;
        }

        System.out.println("Parametros recibidos:");
        for (int i = 0; i < args.length; i++) {
            System.out.println("arg[" + i + "]: " + args[i]);
        }
    }
}

