import java.io.RandomAccessFile;
import java.util.Scanner;

public class P05_Ficheros_Aleatorios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile("personas.dat", "rw")) {
            int eleccion = -1;
            int limiteNombre = 10;

            while (eleccion != 0) {
                System.out.println("--- MENU ---");
                System.out.println("[1] Guardar datos de una persona nueva");
                System.out.println("[2] Mostrar datos de una persona por id");
                System.out.println("[3] Modificar los datos de un registro por su id");
                System.out.println("[4] Modificar los datos de un registro completo por su id");
                System.out.println("[0] Salir");

                System.out.print("> ");
                eleccion = scanner.nextInt();
                scanner.nextLine();

                switch (eleccion) {
                    case 1 -> {
                        int idNuevo = (int) (raf.length() / 40);
                        long posicion = idNuevo * 40L;
                        raf.seek(posicion);
                        raf.writeInt(idNuevo);

                        String nombre = "";
                        while (nombre.trim().isEmpty()) {
                            System.out.print("Dime su nombre: ");
                            nombre = scanner.nextLine();
                        }

                        for (int i = 0; i < limiteNombre; i++) {
                            char letra = (i < nombre.length()) ? nombre.charAt(i) : '\0';
                            raf.writeChar(letra);
                        }

                        double peso = 0.0;
                        while (peso == 0.0) {
                            System.out.print("Dime su peso: ");
                            peso = scanner.nextDouble();
                        }
                        raf.writeDouble(peso);

                        double altura = 0.0;
                        while (altura == 0.0) {
                            System.out.print("Dime su altura: ");
                            altura = scanner.nextDouble();
                        }
                        raf.writeDouble(altura);
                    }
                    case 2 -> {
                        int idIntroducido;
                        System.out.print("Dime el id de la persona que quieres buscar: ");
                        idIntroducido = scanner.nextInt();
                        scanner.nextLine();
                        raf.seek(idIntroducido * 40L);

                        int idLeido = raf.readInt();

                        StringBuilder nombreSb = new StringBuilder();
                        for (int i = 0; i < limiteNombre; i++) {
                            char c = raf.readChar();
                            if (c != '\0') {
                                nombreSb.append(c);
                            }
                        }
                        String nombreBuscado = nombreSb.toString().trim();

                        double pesoBuscado = raf.readDouble();
                        double alturaBuscado = raf.readDouble();

                        double imc = pesoBuscado / (alturaBuscado * alturaBuscado);

                        System.out.println("ID: " + idLeido + " | Nombre: " + nombreBuscado + " | Peso: " + pesoBuscado + " | Altura: " + alturaBuscado + " | IMC: " + imc);
                    }
                    case 3 -> {
                        System.out.println("Dime el id de la persona a la que quieres modificar los datos: ");
                        int idModificar = scanner.nextInt();
                        scanner.nextLine();

                        raf.seek(idModificar * 40L);

                        System.out.println("Que valor deseas modificar:");
                        System.out.println("[1] Nombre");
                        System.out.println("[2] Peso");
                        System.out.println("[3] Altura");
                        System.out.print("> ");
                        int eleccionCambio = scanner.nextInt();

                        long nombreModificar = idModificar * 40L + 4;
                        long pesoModificar = idModificar * 40L + 24;
                        long alturaModificar = idModificar * 40L + 32;

                        switch (eleccionCambio) {
                            case 1 -> {
                                raf.seek(nombreModificar);
                                String nuevonombre = "";
                                while (nuevonombre.trim().isEmpty()) {
                                    System.out.print("Dime el nuevo nombre: ");
                                    nuevonombre = scanner.nextLine();
                                }

                                for (int i = 0; i < limiteNombre; i++) {
                                    char letra = (i < nuevonombre.length()) ? nuevonombre.charAt(i) : '\0';
                                    raf.writeChar(letra);
                                }
                            }
                                case 2 -> {
                                    raf.seek(pesoModificar);
                                    double nuevopeso = 0.0;
                                    while (nuevopeso == 0.0) {
                                        System.out.print("Dime el nuevo peso: ");
                                        nuevopeso = scanner.nextDouble();
                                    }
                                    raf.writeDouble(nuevopeso);
                            }
                                case 3 -> {
                                    raf.seek(alturaModificar);
                                    double nuevaaltura = 0.0;
                                    while (nuevaaltura == 0.0) {
                                        System.out.print("Dime la nueva altura: ");
                                        nuevaaltura = scanner.nextDouble();
                                    }
                                    raf.writeDouble(nuevaaltura);
                            }
                                default -> {
                                    System.out.println("Elige una opcion valida");
                            }
                        }
                    }
                    case 4 -> {
                        System.out.println("Dime el id de la persona a la que quieres modificar los datos: ");
                        int idModificar = scanner.nextInt();
                        scanner.nextLine();

                        raf.seek(idModificar * 40L);

                        long nombreModificarCompleto = idModificar * 40L + 4;
                        long pesoModificarCompleto = idModificar * 40L + 24;
                        long alturaModificarCompleto = idModificar * 40L + 32;

                        raf.seek(nombreModificarCompleto);
                        String nuevonombre = "";
                        while (nuevonombre.trim().isEmpty()) {
                            System.out.print("Dime el nuevo nombre: ");
                            nuevonombre = scanner.nextLine();
                        }
                        for (int i = 0; i < limiteNombre; i++) {
                            char letra = (i < nuevonombre.length()) ? nuevonombre.charAt(i) : '\0';
                            raf.writeChar(letra);
                        }

                        raf.seek(pesoModificarCompleto);
                        double nuevopeso = 0.0;
                        while (nuevopeso == 0.0) {
                            System.out.print("Dime el nuevo peso: ");
                            nuevopeso = scanner.nextDouble();
                        }
                        raf.writeDouble(nuevopeso);

                        raf.seek(alturaModificarCompleto);
                        double nuevaaltura = 0.0;
                        while (nuevaaltura == 0.0) {
                            System.out.print("Dime la nueva altura: ");
                            nuevaaltura = scanner.nextDouble();
                        }
                        raf.writeDouble(nuevaaltura);
                    }
                    case 0 -> System.out.println("Saliendo...");
                    default -> {
                        System.out.println("Opcion no valida...");
                    }
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error en el archivo: " + e.toString());
        }
    }
}