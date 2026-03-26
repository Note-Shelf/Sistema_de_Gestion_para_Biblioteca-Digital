import java.util.ArrayList;

public class GestorPrestamo {

    private ArrayList<Prestamos> lista;

    int maxPrestamos;

    public GestorPrestamo(int maxPrestamos) {
        this.lista = new ArrayList<Prestamos>();
        this.maxPrestamos = maxPrestamos;
    }

    public boolean registroPrestamo(String isbnLibro, String documentoUsuario, Usuario operador, Libro libro) {

        boolean tienePermiso = false;

        boolean esBibliotecario = Validador.textosSonIguales(
                operador.getTipoUsuario(), "BIBLIOTECARIO");
        boolean esAdministrador = Validador.textosSonIguales(
                operador.getTipoUsuario(), "ADMINISTRADOR");

        if (esBibliotecario == true) {
            tienePermiso = true;
        }
        if (esAdministrador == true) {
            tienePermiso = true;
        }

        if (tienePermiso == false) {
            System.out.println("____________________________________");
            System.out.println("ACCESO DENEGADO.");
            System.out.println("Solo Bibliotecario o Administrador");
            System.out.println("pueden registrar Prestamos.");
            System.out.println("____________________________________");
            return false;
        }

        boolean libroDisponible = Validador.textosSonIguales(libro.getEstado(), "Disponible");

        if (libroDisponible == false) {
            System.out.println("____________________________________");
            System.out.println("ERROR: ese libro no esta disponible.");
            System.out.println("Estado actual: " + libro.getEstado());
            System.out.println("Espere a que sea devuelto.");
            System.out.println("____________________________________");
            return false;
        }

        int PrestamosActuales = contarPrestamosPorUsuario(documentoUsuario);

        if (PrestamosActuales >= maxPrestamos) {
            System.out.println("____________________________________");
            System.out.println("CANCELADO: el usuario ya tiene");
            System.out.println(maxPrestamos + " Prestamos activos (el maximo).");
            System.out.println("Debe devolver un libro antes de pedir otro.");
            System.out.println("____________________________________");
            return false;
        }

        String nuevoId = "P" + (lista.size() + 1);
        Prestamos nuevo = new Prestamos(nuevoId, isbnLibro, documentoUsuario);
        lista.add(nuevo);

        System.out.println("____________________________________");
        System.out.println("Prestamo registrado exitosamente.");
        System.out.println("ID del prestamo : " + nuevoId);
        System.out.println("Libro ISBN      : " + isbnLibro);
        System.out.println("Usuario         : " + documentoUsuario);
        System.out.println("Fecha           : " + nuevo.fechaPrestamo);
        System.out.println("____________________________________");
        return true;
    }

    public boolean solicitarPrestamoPropio(String isbnLibro, Usuario lector, Libro libro) {

        boolean esLector = Validador.textosSonIguales(lector.getTipoUsuario(), "LECTOR");

        if (esLector == false) {
            System.out.println("____________________________________");
            System.out.println("ERROR: este metodo es solo para Lectores.");
            System.out.println("____________________________________");
            return false;
        }

        boolean libroDisponible = Validador.textosSonIguales(libro.getEstado(), "Disponible");

        if (libroDisponible == false) {
            System.out.println("____________________________________");
            System.out.println("AVISO: ese libro no esta disponible.");
            System.out.println("Estado: " + libro.getEstado());
            System.out.println("El libro esta prestado. Intente mas tarde.");
            System.out.println("____________________________________");
            return false;
        }

        int PrestamosActuales = contarPrestamosPorUsuario(lector.getDocumento());

        if (PrestamosActuales >= maxPrestamos) {
            System.out.println("____________________________________");
            System.out.println("AVISO: ya tienes " + maxPrestamos + " Prestamos activos.");
            System.out.println("Ese es el maximo permitido.");
            System.out.println("Debes devolver un libro antes de pedir otro.");
            System.out.println("____________________________________");
            return false;
        }

        String nuevoId = "P" + (lista.size() + 1);
        Prestamos nuevo = new Prestamos(nuevoId, isbnLibro, lector.getDocumento());
        lista.add(nuevo);

        System.out.println("____________________________________");
        System.out.println("Prestamo solicitado exitosamente.");
        System.out.println("ID del prestamo : " + nuevoId);
        System.out.println("Libro ISBN      : " + isbnLibro);
        System.out.println("Fecha           : " + nuevo.fechaPrestamo);
        System.out.println("____________________________________");
        return true;
    }

    public void PrestamosActivos() {

        System.out.println("____________________________________");
        System.out.println("Prestamos ACTIVOS DEL SISTEMA");
        System.out.println("------------------------------------");

        int contador = 0;

        for (int i = 0; i < lista.size(); i++) {
            Prestamos p = lista.get(i);

            if (p.activo == true) {
                System.out.println("ID: " + p.id
                        + " | ISBN: " + p.isbnLibro
                        + " | Usuario: " + p.documentoUsuario
                        + " | Fecha: " + p.fechaPrestamo);
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay Prestamos activos en este momento.");
        } else {
            System.out.println("------------------------------------");
            System.out.println("Total de Prestamos activos: " + contador);
        }

        System.out.println("____________________________________");
    }

    public void PrestamosActivosPorUsuario(String documentoUsuario) {

        System.out.println("____________________________________");
        System.out.println("MIS Prestamos ACTIVOS");
        System.out.println("------------------------------------");

        int contador = 0;

        for (int i = 0; i < lista.size(); i++) {
            Prestamos p = lista.get(i);

            if (p.activo == true) {
                boolean esDelUsuario = Validador.textosSonIguales(
                        p.documentoUsuario, documentoUsuario);

                if (esDelUsuario == true) {
                    System.out.println("ID: " + p.id
                            + " | ISBN: " + p.isbnLibro
                            + " | Fecha: " + p.fechaPrestamo);
                    contador++;
                }
            }
        }

        if (contador == 0) {
            System.out.println("No tienes Prestamos activos en este momento.");
        } else {
            System.out.println("------------------------------------");
            System.out.println("Total: " + contador + " prestamo(s) activo(s).");
        }

        System.out.println("____________________________________");
    }

    public boolean devolverPorDocumentoIsbn(String documento, String isbn) {

        for (int i = 0; i < lista.size(); i++) {
            Prestamos p = lista.get(i);

            if (p.activo == true) {
                boolean documentoCorrecto = Validador.textosSonIguales(
                        p.documentoUsuario, documento);

                if (documentoCorrecto == true) {
                    boolean isbnCorrecto = Validador.textosSonIguales(p.isbnLibro, isbn);

                    if (isbnCorrecto == true) {
                        p.devolucionLibro();

                        System.out.println("____________________________________");
                        System.out.println("Devolucion registrada correctamente.");
                        System.out.println("ID del prestamo cerrado : " + p.id);
                        System.out.println("Fecha de devolucion     : "
                                + java.time.LocalDate.now());
                        System.out.println("____________________________________");
                        return true;
                    }
                }
            }
        }

        System.out.println("____________________________________");
        System.out.println("AVISO: no se encontro prestamo activo");
        System.out.println("para ese usuario con ese libro.");
        System.out.println("Verifique el documento y el ISBN.");
        System.out.println("____________________________________");
        return false;
    }

    public boolean UsuarioTienePrestamosActivos(String documentoUsuario) {

        int cantidad = contarPrestamosPorUsuario(documentoUsuario);

        if (cantidad > 0) {
            return true;
        }

        return false;
    }

    public boolean libroTienePrestamosActivos(String isbnLibro) {

        for (int i = 0; i < lista.size(); i++) {
            Prestamos p = lista.get(i);

            if (p.activo == true) {
                boolean mismoLibro = Validador.textosSonIguales(p.isbnLibro, isbnLibro);

                if (mismoLibro == true) {
                    return true;
                }
            }
        }

        return false;
    }

    private int contarPrestamosPorUsuario(String documentoUsuario) {

        int contador = 0;

        for (int i = 0; i < lista.size(); i++) {
            Prestamos p = lista.get(i);

            if (p.activo == true) {
                boolean esDelUsuario = Validador.textosSonIguales(
                        p.documentoUsuario, documentoUsuario);

                if (esDelUsuario == true) {
                    contador++;
                }
            }
        }

        return contador;
    }
}