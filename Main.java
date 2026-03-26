import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int maxIntentos = biblioteca.getMaxIntentosLogin();
        int intentosFallidos = 0;

        System.out.println("____________________________________");
        System.out.println(biblioteca.getNombreBiblioteca());
        System.out.println("Version: " + biblioteca.getVersionSistema());
        System.out.println("Max prestamos por Usuario: " + biblioteca.getMaxPrestamos());
        System.out.println("____________________________________");
        System.out.println("Bienvenido. Por favor inicie sesion.");
        System.out.println("____________________________________");

        boolean sistemaActivo = true;

        while (sistemaActivo == true) {

            if (intentosFallidos >= maxIntentos) {
                System.out.println("____________________________________");
                System.out.println("SISTEMA BLOQUEADO.");
                System.out.println("Se supero el limite de " + maxIntentos + " intentos.");
                System.out.println("Comuniquese con el administrador.");
                System.out.println("____________________________________");
                break;
            }

            System.out.println("____________________________________");
            System.out.println("INICIO DE SESION");
            System.out.println("Intento " + (intentosFallidos + 1) + " de " + maxIntentos);
            System.out.println("------------------------------------");
            System.out.print("Usuario (documento): ");
            String documento = scanner.nextLine();

            System.out.print("Contraseña         : ");
            String Contraseña = scanner.nextLine();

            Usuario UsuarioActual = biblioteca.iniciarSesion(documento, Contraseña);

            if (UsuarioActual == null) {

                intentosFallidos++;
                int restantes = maxIntentos - intentosFallidos;

                System.out.println("____________________________________");
                System.out.println("AVISO: Usuario o Contraseña incorrectos.");

                if (restantes > 0) {
                    System.out.println("Intentos restantes: " + restantes);
                }

                System.out.println("____________________________________");

            } else {

                intentosFallidos = 0;

                System.out.println("____________________________________");
                System.out.println("Sesion iniciada correctamente.");
                System.out.println("Bienvenido: " + UsuarioActual.getNombreCompleto());

                System.out.println("Rol       : " + UsuarioActual.getTipoUsuario());

                System.out.println("Acceso    : " + UsuarioActual.obtenerDescripcionRol());
                System.out.println("____________________________________");

                boolean esAdmin = Validador.textosSonIguales(
                        UsuarioActual.getTipoUsuario(), "ADMINISTRADOR");
                boolean esBiblio = Validador.textosSonIguales(
                        UsuarioActual.getTipoUsuario(), "BIBLIOTECARIO");
                boolean esLector = Validador.textosSonIguales(
                        UsuarioActual.getTipoUsuario(), "LECTOR");

                if (esAdmin == true) {
                    Menus.menuAdministrador(scanner, biblioteca, UsuarioActual);
                }
                if (esBiblio == true) {
                    Menus.menuBibliotecario(scanner, biblioteca, UsuarioActual);
                }
                if (esLector == true) {
                    Menus.menuLector(scanner, biblioteca, UsuarioActual);
                }

            }
        }

        scanner.close();
    }
}