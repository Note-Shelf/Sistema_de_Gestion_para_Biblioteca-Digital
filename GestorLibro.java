import java.util.ArrayList;
import java.time.LocalDate;

public class GestorLibro {

    private ArrayList<Libro> lista;

    public GestorLibro() {

        lista = new ArrayList<>();

        Libro libro1 = new Libro(
                "978-84-376-0494-7",
                "Cien anos de soledad",
                "Gabriel Garcia Marquez",
                1967);
        Libro libro2 = new Libro(
                "978-84-204-6454-5",
                "El amor en los tiempos del colera",
                "Gabriel Garcia Marquez",
                1985);
        Libro libro3 = new Libro(
                "978-84-376-0155-7",
                "Don Quijote de la Mancha",
                "Miguel de Cervantes",
                1605);

        lista.add(libro1);
        lista.add(libro2);
        lista.add(libro3);
    }

    public boolean registrarLibro(String isbn, String titulo, String autor, int anio) {

        Libro libroTemporal = new Libro(isbn, titulo, autor, anio);
        boolean camposValidos = libroTemporal.validarCampos();

        if (camposValidos == false) {
            return false;
        }

        int anioActual = LocalDate.now().getYear();

        if (anio > anioActual) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el ano " + anio + " es futuro.");
            System.out.println("El ano actual es: " + anioActual);
            System.out.println("____________________________________");
            return false;
        }

        if (anio <= 0) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el ano debe ser un numero positivo.");
            System.out.println("Ejemplo: 1985, 2001, 2020");
            System.out.println("____________________________________");
            return false;
        }

        for (int i = 0; i < lista.size(); i++) {
            Libro l = lista.get(i);
            boolean isbnRepetido = Validador.textosSonIguales(l.getIsbn(), isbn);

            if (isbnRepetido == true) {
                System.out.println("____________________________________");
                System.out.println("ERROR: ya existe un libro con ISBN: " + isbn);
                System.out.println("Cada libro debe tener un ISBN unico.");
                System.out.println("____________________________________");
                return false;
            }
        }

        lista.add(libroTemporal);
        return true;
    }

    public void listarLibros() {

        if (lista.size() == 0) {
            System.out.println("____________________________________");
            System.out.println("No hay libros registrados.");
            System.out.println("____________________________________");
            return;
        }

        System.out.println("____________________________________");
        System.out.println("LISTA DE LIBROS (" + lista.size() + " en total)");
        System.out.println("------------------------------------");

        for (int i = 0; i < lista.size(); i++) {
            Libro l = lista.get(i);
            System.out.println((i + 1) + ". ISBN   : " + l.getIsbn());
            System.out.println("   Titulo : " + l.getTitulo());
            System.out.println("   Autor  : " + l.getAutor());
            System.out.println("   Ano    : " + l.getAnioPublicacion());
            System.out.println("   Estado : " + l.getEstado());
            System.out.println("   -");
        }

        System.out.println("____________________________________");
    }

    public void buscarLibroPorISBN(String isbn) {

        boolean isbnVacio = Validador.textoEstaVacio(isbn);
        if (isbnVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el ISBN no puede estar vacio.");
            System.out.println("____________________________________");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Libro l = lista.get(i);
            boolean encontrado = Validador.textosSonIguales(l.getIsbn(), isbn);

            if (encontrado == true) {
                System.out.println("____________________________________");
                System.out.println("LIBRO ENCONTRADO");
                System.out.println("------------------------------------");
                System.out.println("ISBN   : " + l.getIsbn());
                System.out.println("Titulo : " + l.getTitulo());
                System.out.println("Autor  : " + l.getAutor());
                System.out.println("Ano    : " + l.getAnioPublicacion());
                System.out.println("Estado : " + l.getEstado());
                System.out.println("____________________________________");
                return;
            }
        }

        System.out.println("____________________________________");
        System.out.println("No se encontro libro con ISBN: " + isbn);
        System.out.println("Verifique que el ISBN sea correcto.");
        System.out.println("____________________________________");
    }

    public void actualizarDisponibilidad(String isbn, String estado) {

        String estadoMinusculas = estado.toLowerCase();

        boolean esDisponible = Validador.textosSonIguales(estadoMinusculas, "disponible");
        boolean esPrestado = Validador.textosSonIguales(estadoMinusculas, "prestado");

        boolean estadoValido = false;
        if (esDisponible == true) {
            estadoValido = true;
        }
        if (esPrestado == true) {
            estadoValido = true;
        }

        if (estadoValido == false) {
            System.out.println("____________________________________");
            System.out.println("ERROR: estado no valido.");
            System.out.println("Escriba: disponible  o  prestado");
            System.out.println("____________________________________");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Libro l = lista.get(i);
            boolean mismoIsbn = Validador.textosSonIguales(l.getIsbn(), isbn);

            if (mismoIsbn == true) {
                if (esDisponible == true) {
                    l.actualizarDisponibilidad("Disponible");
                }
                if (esPrestado == true) {
                    l.actualizarDisponibilidad("Prestado");
                }

                System.out.println("____________________________________");
                System.out.println("Estado actualizado: " + l.getEstado());
                System.out.println("____________________________________");
                return;
            }
        }

        System.out.println("____________________________________");
        System.out.println("No se encontro libro con ISBN: " + isbn);
        System.out.println("____________________________________");
    }

    public boolean eliminarLibro(String isbn, GestorPrestamo GestorPrestamo) {

        boolean isbnVacio = Validador.textoEstaVacio(isbn);
        if (isbnVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el ISBN no puede estar vacio.");
            System.out.println("____________________________________");
            return false;
        }

        boolean tienePrestamoActivo = GestorPrestamo.libroTienePrestamosActivos(isbn);

        if (tienePrestamoActivo == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: no se puede eliminar ese libro.");
            System.out.println("El libro tiene un Prestamos activo.");
            System.out.println("Primero debe ser devuelto.");
            System.out.println("____________________________________");
            return false;
        }

        for (int i = 0; i < lista.size(); i++) {
            Libro l = lista.get(i);
            boolean mismoIsbn = Validador.textosSonIguales(l.getIsbn(), isbn);

            if (mismoIsbn == true) {
                lista.remove(i);
                return true;
            }
        }

        System.out.println("____________________________________");
        System.out.println("No se encontro libro con ISBN: " + isbn);
        System.out.println("____________________________________");
        return false;
    }

    public Libro buscarLibroObjeto(String isbn) {

        for (int i = 0; i < lista.size(); i++) {
            Libro l = lista.get(i);
            boolean mismoIsbn = Validador.textosSonIguales(l.getIsbn(), isbn);

            if (mismoIsbn == true) {
                return l;
            }
        }

        return null;
    }
}