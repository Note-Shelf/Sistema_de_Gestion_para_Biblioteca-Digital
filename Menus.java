import java.util.Scanner;

public class Menus {

    public static void menuAdministrador(Scanner scanner, Biblioteca biblioteca,
            Usuario UsuarioActual) {

        boolean sesionActiva = true;

        while (sesionActiva == true) {
            System.out.println("____________________________________");
            System.out.println("MENU PRINCIPAL - ADMINISTRADOR");
            System.out.println("------------------------------------");
            System.out.println("1 - Gestion de libros");
            System.out.println("2 - Gestion de Usuarios");
            System.out.println("3 - Gestion de prestamos");
            System.out.println("4 - Cerrar sesion");
            System.out.println("5 - Salir del sistema");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opcion (1-5): ");

            int opcion = leerOpcion(scanner);

            if (opcion == 1) {
                menuLibrosCompleto(scanner, biblioteca);
            }
            if (opcion == 2) {
                menuUsuariosAdministrador(scanner, biblioteca, UsuarioActual);
            }
            if (opcion == 3) {
                menuPrestamosCompleto(scanner, biblioteca, UsuarioActual);
            }

            if (opcion == 4) {
                System.out.println("____________________________________");
                System.out.println("Sesion cerrada. Hasta luego, "
                        + UsuarioActual.getNombreCompleto() + ".");
                System.out.println("____________________________________");
                sesionActiva = false;
            }

            if (opcion == 5) {
                System.out.println("____________________________________");
                System.out.println("Sistema cerrado. Hasta luego.");
                System.out.println("____________________________________");
                scanner.close();
                System.exit(0);
            }

            if (opcion == 0) {
                System.out.println("____________________________________");
                System.out.println("Opcion no valida. Ingrese un numero del 1 al 5.");
                System.out.println("____________________________________");
            }
        }
    }

    public static void menuBibliotecario(Scanner scanner, Biblioteca biblioteca,
            Usuario UsuarioActual) {

        boolean sesionActiva = true;

        while (sesionActiva == true) {
            System.out.println("____________________________________");
            System.out.println("MENU PRINCIPAL - BIBLIOTECARIO");
            System.out.println("------------------------------------");
            System.out.println("1 - Gestion de libros");
            System.out.println("2 - Consultar Usuarios");
            System.out.println("3 - Gestion de prestamos");
            System.out.println("4 - Cerrar sesion");
            System.out.println("5 - Salir del sistema");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opcion (1-5): ");

            int opcion = leerOpcion(scanner);

            if (opcion == 1) {
                menuLibrosCompleto(scanner, biblioteca);
            }
            if (opcion == 2) {

                menuConsultaUsuarios(scanner, biblioteca);
            }
            if (opcion == 3) {
                menuPrestamosCompleto(scanner, biblioteca, UsuarioActual);
            }

            if (opcion == 4) {
                System.out.println("____________________________________");
                System.out.println("Sesion cerrada. Hasta luego, "
                        + UsuarioActual.getNombreCompleto() + ".");
                System.out.println("____________________________________");
                sesionActiva = false;
            }

            if (opcion == 5) {
                System.out.println("____________________________________");
                System.out.println("Sistema cerrado. Hasta luego.");
                System.out.println("____________________________________");
                scanner.close();
                System.exit(0);
            }

            if (opcion == 0) {
                System.out.println("____________________________________");
                System.out.println("Opcion no valida. Ingrese un numero del 1 al 5.");
                System.out.println("____________________________________");
            }
        }
    }

    public static void menuLector(Scanner scanner, Biblioteca biblioteca,
            Usuario UsuarioActual) {

        boolean sesionActiva = true;

        while (sesionActiva == true) {
            System.out.println("____________________________________");
            System.out.println("MENU PRINCIPAL - LECTOR");
            System.out.println("Sesion: " + UsuarioActual.getNombreCompleto());
            System.out.println("------------------------------------");
            System.out.println("1 - Ver listado de libros");
            System.out.println("2 - Buscar libro por ISBN");
            System.out.println("3 - Solicitar prestamo de un libro");
            System.out.println("4 - Devolver un libro mio");
            System.out.println("5 - Mis prestamos activos");
            System.out.println("6 - Cerrar sesion");
            System.out.println("7 - Salir del sistema");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opcion (1-7): ");

            int opcion = leerOpcion(scanner);

            if (opcion == 1) {
                biblioteca.listarLibros();
            }

            if (opcion == 2) {
                System.out.print("Ingrese el ISBN del libro a buscar: ");
                String isbn = scanner.nextLine();
                biblioteca.buscarLibroPorISBN(isbn);
            }

            if (opcion == 3) {
                System.out.println("____________________________________");
                System.out.println("SOLICITAR PRESTAMO");
                System.out.println("Su documento se usa automaticamente.");
                System.out.println("------------------------------------");
                System.out.print("ISBN del libro que desea pedir: ");
                String isbn = scanner.nextLine();

                boolean isbnVacio = Validador.textoEstaVacio(isbn);

                if (isbnVacio == true) {
                    System.out.println("____________________________________");
                    System.out.println("ERROR: el ISBN no puede estar vacio.");
                    System.out.println("____________________________________");
                } else {
                    boolean resultado = biblioteca.solicitarMiPrestamo(isbn, UsuarioActual);
                    if (resultado == false) {
                        System.out.println("No se pudo completar la solicitud.");
                    }
                }
            }

            if (opcion == 4) {
                System.out.println("____________________________________");
                System.out.println("DEVOLVER UN LIBRO");
                System.out.println("Su documento se usa automaticamente.");
                System.out.println("------------------------------------");
                System.out.print("ISBN del libro a devolver: ");
                String isbn = scanner.nextLine();

                boolean isbnVacio = Validador.textoEstaVacio(isbn);

                if (isbnVacio == true) {
                    System.out.println("____________________________________");
                    System.out.println("ERROR: el ISBN no puede estar vacio.");
                    System.out.println("____________________________________");
                } else {

                    boolean resultado = biblioteca.registrarDevolucion(
                            UsuarioActual.getDocumento(), isbn);
                    if (resultado == false) {
                        System.out.println("No se pudo procesar la devolucion.");
                        System.out.println("Verifique que ese libro este en sus prestamos.");
                    }
                }
            }

            if (opcion == 5) {

                biblioteca.consultarMisPrestamos(UsuarioActual.getDocumento());
            }

            if (opcion == 6) {
                System.out.println("____________________________________");
                System.out.println("Sesion cerrada. Hasta luego, "
                        + UsuarioActual.getNombreCompleto() + ".");
                System.out.println("____________________________________");
                sesionActiva = false;
            }

            if (opcion == 7) {
                System.out.println("____________________________________");
                System.out.println("Sistema cerrado. Hasta luego.");
                System.out.println("____________________________________");
                scanner.close();
                System.exit(0);
            }

            if (opcion == 0) {
                System.out.println("____________________________________");
                System.out.println("Opcion no valida. Ingrese un numero del 1 al 7.");
                System.out.println("____________________________________");
            }
        }
    }

    private static void menuLibrosCompleto(Scanner scanner, Biblioteca biblioteca) {

        boolean volver = false;

        while (volver == false) {
            System.out.println("____________________________________");
            System.out.println("GESTION DE LIBROS");
            System.out.println("------------------------------------");
            System.out.println("1 - Registrar libro nuevo");
            System.out.println("2 - Listar todos los libros");
            System.out.println("3 - Buscar libro por ISBN");
            System.out.println("4 - Actualizar disponibilidad");
            System.out.println("5 - Eliminar libro");
            System.out.println("6 - Volver al menu anterior");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opcion (1-6): ");

            int opcion = leerOpcion(scanner);

            if (opcion == 1) {
                System.out.println("____________________________________");
                System.out.println("REGISTRAR LIBRO NUEVO");
                System.out.println("------------------------------------");
                System.out.print("ISBN             : ");
                String isbn = scanner.nextLine();
                System.out.print("Titulo           : ");
                String titulo = scanner.nextLine();
                System.out.print("Autor            : ");
                String autor = scanner.nextLine();
                System.out.print("Ano de publicacion: ");
                int anio = leerEntero(scanner);

                boolean resultado = biblioteca.registrarLibro(isbn, titulo, autor, anio);

                if (resultado == true) {
                    System.out.println("____________________________________");
                    System.out.println("Libro registrado exitosamente.");
                    System.out.println("____________________________________");
                } else {
                    System.out.println("____________________________________");
                    System.out.println("No se pudo registrar el libro.");
                    System.out.println("Revise los errores mostrados arriba.");
                    System.out.println("____________________________________");
                }
            }

            if (opcion == 2) {
                biblioteca.listarLibros();
            }

            if (opcion == 3) {
                System.out.print("ISBN del libro a buscar: ");
                String isbn = scanner.nextLine();
                biblioteca.buscarLibroPorISBN(isbn);
            }

            if (opcion == 4) {
                System.out.println("____________________________________");
                System.out.println("ACTUALIZAR DISPONIBILIDAD");
                System.out.println("------------------------------------");
                System.out.print("ISBN del libro: ");
                String isbn = scanner.nextLine();
                System.out.println("Escriba el nuevo estado:");
                System.out.println("  disponible  o  prestado");
                System.out.print("Nuevo estado: ");
                String estado = scanner.nextLine();
                biblioteca.actualizarDisponibilidad(isbn, estado);
            }

            if (opcion == 5) {
                System.out.println("____________________________________");
                System.out.println("ELIMINAR LIBRO");
                System.out.println("ATENCION: esta accion no se puede deshacer.");
                System.out.println("------------------------------------");
                System.out.print("ISBN del libro a eliminar: ");
                String isbn = scanner.nextLine();
                System.out.print("Confirme escribiendo: si -> ");
                String confirmacion = scanner.nextLine();
                String confMin = confirmacion.toLowerCase();
                boolean confirmo = Validador.textosSonIguales(confMin, "si");

                if (confirmo == true) {
                    boolean resultado = biblioteca.eliminarLibro(isbn);
                    if (resultado == true) {
                        System.out.println("____________________________________");
                        System.out.println("Libro eliminado exitosamente.");
                        System.out.println("____________________________________");
                    } else {
                        System.out.println("____________________________________");
                        System.out.println("No se pudo eliminar el libro.");
                        System.out.println("____________________________________");
                    }
                } else {
                    System.out.println("____________________________________");
                    System.out.println("Operacion cancelada.");
                    System.out.println("____________________________________");
                }
            }

            if (opcion == 6) {
                volver = true;
            }

            if (opcion == 0) {
                System.out.println("____________________________________");
                System.out.println("Opcion no valida. Ingrese un numero del 1 al 6.");
                System.out.println("____________________________________");
            }
        }
    }

    private static void menuUsuariosAdministrador(Scanner scanner, Biblioteca biblioteca,
            Usuario operador) {

        boolean volver = false;

        while (volver == false) {
            System.out.println("____________________________________");
            System.out.println("GESTION DE Usuario - ADMINISTRADOR");
            System.out.println("------------------------------------");
            System.out.println("1 - Registrar Usuario nuevo");
            System.out.println("2 - Listar todos los Usuarios");
            System.out.println("3 - Buscar Usuario por documento");
            System.out.println("4 - Eliminar Usuario");
            System.out.println("5 - Volver al menu anterior");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opcion (1-5): ");

            int opcion = leerOpcion(scanner);

            if (opcion == 1) {
                System.out.println("____________________________________");
                System.out.println("REGISTRAR Usuario NUEVO");
                System.out.println("------------------------------------");
                System.out.print("Documento       : ");
                String documento = scanner.nextLine();
                System.out.print("Nombre completo : ");
                String nombre = scanner.nextLine();
                String tipo = "";
                boolean tipoValido = false;

                while (tipoValido == false) {
                    System.out.println("Tipo de Usuario:");
                    System.out.println("1 - Administrador");
                    System.out.println("2 - Bibliotecario");
                    System.out.println("3 - Lector");
                    System.out.print("Seleccione el rol (1-3): ");

                    int tipoOpcion = leerOpcion(scanner);

                    if (tipoOpcion == 1) {
                        tipo = "administrador";
                        tipoValido = true;
                    } else if (tipoOpcion == 2) {
                        tipo = "bibliotecario";
                        tipoValido = true;
                    } else if (tipoOpcion == 3) {
                        tipo = "lector";
                        tipoValido = true;
                    } else {
                        System.out.println("____________________________________");
                        System.out.println("Opcion no valida. Ingrese un numero del 1 al 3.");
                        System.out.println("____________________________________");
                    }
                }

                System.out.println("Contraseña (minimo 6 caracteres):");
                System.out.print("Contraseña: ");
                String Contraseña = scanner.nextLine();

                boolean resultado = biblioteca.registrarUsuario(documento, nombre, tipo, Contraseña);

                if (resultado == true) {
                    System.out.println("____________________________________");
                    System.out.println("Usuario registrado exitosamente.");
                    System.out.println("____________________________________");
                } else {
                    System.out.println("____________________________________");
                    System.out.println("No se pudo registrar el Usuario.");
                    System.out.println("Revise los errores mostrados arriba.");
                    System.out.println("____________________________________");
                }
            }

            if (opcion == 2) {
                biblioteca.listarUsuarios();
            }

            if (opcion == 3) {
                System.out.print("Documento del Usuario a buscar: ");
                String documento = scanner.nextLine();
                biblioteca.buscarUsuarioPorDocumento(documento);
            }

            if (opcion == 4) {
                System.out.println("____________________________________");
                System.out.println("ELIMINAR Usuario");
                System.out.println("ATENCION: esta accion no se puede deshacer.");
                System.out.println("------------------------------------");
                System.out.print("Documento del Usuario a eliminar: ");
                String documento = scanner.nextLine();
                System.out.print("Confirme escribiendo: si -> ");
                String confirmacion = scanner.nextLine();
                String confMin = confirmacion.toLowerCase();
                boolean confirmo = Validador.textosSonIguales(confMin, "si");

                if (confirmo == true) {
                    boolean resultado = biblioteca.eliminarUsuario(documento, operador);
                    if (resultado == true) {
                        System.out.println("____________________________________");
                        System.out.println("Usuario eliminado exitosamente.");
                        System.out.println("____________________________________");
                    } else {
                        System.out.println("____________________________________");
                        System.out.println("No se pudo eliminar el Usuario.");
                        System.out.println("____________________________________");
                    }
                } else {
                    System.out.println("____________________________________");
                    System.out.println("Operacion cancelada.");
                    System.out.println("____________________________________");
                }
            }

            if (opcion == 5) {
                volver = true;
            }

            if (opcion == 0) {
                System.out.println("____________________________________");
                System.out.println("Opcion no valida. Ingrese un numero del 1 al 5.");
                System.out.println("____________________________________");
            }
        }
    }

    private static void menuConsultaUsuarios(Scanner scanner, Biblioteca biblioteca) {

        boolean volver = false;

        while (volver == false) {
            System.out.println("____________________________________");
            System.out.println("CONSULTAR UsuarioS - BIBLIOTECARIO");
            System.out.println("(Solo consulta: no puede registrar ni eliminar)");
            System.out.println("------------------------------------");
            System.out.println("1 - Listar todos los Usuarios");
            System.out.println("2 - Buscar Usuario por documento");
            System.out.println("3 - Volver al menu anterior");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opcion (1-3): ");

            int opcion = leerOpcion(scanner);

            if (opcion == 1) {
                biblioteca.listarUsuarios();
            }

            if (opcion == 2) {
                System.out.print("Documento del Usuario a buscar: ");
                String documento = scanner.nextLine();
                biblioteca.buscarUsuarioPorDocumento(documento);
            }

            if (opcion == 3) {
                volver = true;
            }

            if (opcion == 0) {
                System.out.println("____________________________________");
                System.out.println("Opcion no valida. Ingrese un numero del 1 al 3.");
                System.out.println("____________________________________");
            }
        }
    }

    private static void menuPrestamosCompleto(Scanner scanner, Biblioteca biblioteca,
            Usuario operador) {

        boolean volver = false;

        while (volver == false) {
            System.out.println("____________________________________");
            System.out.println("GESTION DE PRESTAMOS");
            System.out.println("------------------------------------");
            System.out.println("1 - Registrar prestamo para un Usuario");
            System.out.println("2 - Registrar devolucion de un libro");
            System.out.println("3 - Ver todos los prestamos activos");
            System.out.println("4 - Volver al menu anterior");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opcion (1-4): ");

            int opcion = leerOpcion(scanner);

            if (opcion == 1) {
                System.out.println("____________________________________");
                System.out.println("REGISTRAR PRESTAMO");
                System.out.println("------------------------------------");
                System.out.print("Documento del Usuario que recibe el libro: ");
                String documento = scanner.nextLine();
                System.out.print("ISBN del libro a prestar: ");
                String isbn = scanner.nextLine();

                boolean docVacio = Validador.textoEstaVacio(documento);
                boolean isbnVacio = Validador.textoEstaVacio(isbn);

                if (docVacio == true) {
                    System.out.println("____________________________________");
                    System.out.println("ERROR: el documento no puede estar vacio.");
                    System.out.println("____________________________________");
                } else {
                    if (isbnVacio == true) {
                        System.out.println("____________________________________");
                        System.out.println("ERROR: el ISBN no puede estar vacio.");
                        System.out.println("____________________________________");
                    } else {
                        boolean resultado = biblioteca.registrarPrestamo(
                                documento, isbn, operador);
                        if (resultado == false) {
                            System.out.println("____________________________________");
                            System.out.println("No se pudo registrar el prestamo.");
                            System.out.println("Revise los errores mostrados arriba.");
                            System.out.println("____________________________________");
                        }
                    }
                }
            }

            if (opcion == 2) {
                System.out.println("____________________________________");
                System.out.println("REGISTRAR DEVOLUCION");
                System.out.println("------------------------------------");
                System.out.print("Documento del Usuario que devuelve: ");
                String documento = scanner.nextLine();
                System.out.print("ISBN del libro devuelto: ");
                String isbn = scanner.nextLine();

                boolean docVacio = Validador.textoEstaVacio(documento);
                boolean isbnVacio = Validador.textoEstaVacio(isbn);

                if (docVacio == true) {
                    System.out.println("____________________________________");
                    System.out.println("ERROR: el documento no puede estar vacio.");
                    System.out.println("____________________________________");
                } else {
                    if (isbnVacio == true) {
                        System.out.println("____________________________________");
                        System.out.println("ERROR: el ISBN no puede estar vacio.");
                        System.out.println("____________________________________");
                    } else {
                        boolean resultado = biblioteca.registrarDevolucion(documento, isbn);
                        if (resultado == false) {
                            System.out.println("____________________________________");
                            System.out.println("No se pudo registrar la devolucion.");
                            System.out.println("Verifique el documento y el ISBN.");
                            System.out.println("____________________________________");
                        }
                    }
                }
            }

            if (opcion == 3) {
                biblioteca.consultarPrestamosActivos();
            }

            if (opcion == 4) {
                volver = true;
            }

            if (opcion == 0) {
                System.out.println("____________________________________");
                System.out.println("Opcion no valida. Ingrese un numero del 1 al 4.");
                System.out.println("____________________________________");
            }
        }
    }

    private static int leerOpcion(Scanner scanner) {

        String entrada = scanner.nextLine();

        if (entrada.length() == 0) {
            return 0;
        }

        String primerCaracter = entrada.substring(0, 1);

        if (Validador.textosSonIguales(primerCaracter, "1")) {
            return 1;
        }
        if (Validador.textosSonIguales(primerCaracter, "2")) {
            return 2;
        }
        if (Validador.textosSonIguales(primerCaracter, "3")) {
            return 3;
        }
        if (Validador.textosSonIguales(primerCaracter, "4")) {
            return 4;
        }
        if (Validador.textosSonIguales(primerCaracter, "5")) {
            return 5;
        }
        if (Validador.textosSonIguales(primerCaracter, "6")) {
            return 6;
        }
        if (Validador.textosSonIguales(primerCaracter, "7")) {
            return 7;
        }

        return 0;
    }

    private static int leerEntero(Scanner scanner) {

        String entrada = scanner.nextLine();
        int numero = 0;

        try {
            numero = Integer.parseInt(entrada);

        } catch (NumberFormatException e) {
            System.out.println("____________________________________");
            System.out.println("AVISO: se esperaba un numero entero.");
            System.out.println("Ejemplo valido: 1985, 2001, 2020");
            System.out.println("Se usara 0 por defecto.");
            System.out.println("____________________________________");
            numero = 0;
        }

        return numero;
    }
}