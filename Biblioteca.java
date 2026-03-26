public class Biblioteca {

    private GestorLibro gestorLibro;
    private GestorUsuario gestorUsuario;
    private GestorPrestamo GestorPrestamo;
    private Configuracion config;

    public Biblioteca() {

        config = new Configuracion();

        GestorPrestamo = new GestorPrestamo(config.getMaxPrestamos());

        gestorLibro = new GestorLibro();
        gestorUsuario = new GestorUsuario();
    }

    public String getNombreBiblioteca() {
        return config.getNombreBiblioteca();
    }

    public String getVersionSistema() {
        return config.getVersion();
    }

    public int getMaxIntentosLogin() {
        return config.getMaxIntentosLogin();
    }

    public int getMaxPrestamos() {
        return config.getMaxPrestamos();
    }

    public Usuario iniciarSesion(String documento, String Contraseña) {
        return gestorUsuario.iniciarSesion(documento, Contraseña);
    }

    public boolean registrarUsuario(String documento, String nombre,
            String tipo, String Contraseña) {
        return gestorUsuario.registrarUsuario(documento, nombre, tipo, Contraseña);
    }

    public void listarUsuarios() {
        gestorUsuario.listarUsuarios();
    }

    public void buscarUsuarioPorDocumento(String documento) {
        gestorUsuario.buscarUsuarioPorDocumento(documento);
    }

    public boolean eliminarUsuario(String documento, Usuario operador) {
        return gestorUsuario.eliminarUsuario(documento, operador, GestorPrestamo);
    }

    public boolean registrarLibro(String isbn, String titulo, String autor, int anio) {
        return gestorLibro.registrarLibro(isbn, titulo, autor, anio);
    }

    public void listarLibros() {
        gestorLibro.listarLibros();
    }

    public void buscarLibroPorISBN(String isbn) {
        gestorLibro.buscarLibroPorISBN(isbn);
    }

    public void actualizarDisponibilidad(String isbn, String estado) {
        gestorLibro.actualizarDisponibilidad(isbn, estado);
    }

    public boolean eliminarLibro(String isbn) {
        return gestorLibro.eliminarLibro(isbn, GestorPrestamo);
    }

    public boolean registrarPrestamo(String documentoUsuario, String isbnLibro,
            Usuario operador) {

        Libro libroEncontrado = gestorLibro.buscarLibroObjeto(isbnLibro);

        if (libroEncontrado == null) {
            System.out.println("____________________________________");
            System.out.println("ERROR: no se encontro libro con ISBN: " + isbnLibro);
            System.out.println("____________________________________");
            return false;
        }

        Usuario UsuarioEncontrado = gestorUsuario.buscarUsuarioObjeto(documentoUsuario);

        if (UsuarioEncontrado == null) {
            System.out.println("____________________________________");
            System.out.println("ERROR: no se encontro Usuario con documento: "
                    + documentoUsuario);
            System.out.println("____________________________________");
            return false;
        }

        boolean prestamoOk = GestorPrestamo.registroPrestamo(
                isbnLibro, documentoUsuario, operador, libroEncontrado);

        if (prestamoOk == true) {
            libroEncontrado.actualizarDisponibilidad("Prestado");
            return true;
        }

        return false;
    }

    public boolean solicitarMiPrestamo(String isbnLibro, Usuario lector) {

        Libro libroEncontrado = gestorLibro.buscarLibroObjeto(isbnLibro);

        if (libroEncontrado == null) {
            System.out.println("____________________________________");
            System.out.println("ERROR: no se encontro libro con ISBN: " + isbnLibro);
            System.out.println("Verifique el ISBN e intente de nuevo.");
            System.out.println("____________________________________");
            return false;
        }

        boolean prestamoOk = GestorPrestamo.solicitarPrestamoPropio(
                isbnLibro, lector, libroEncontrado);

        if (prestamoOk == true) {
            libroEncontrado.actualizarDisponibilidad("Prestado");
            return true;
        }

        return false;
    }

    public boolean registrarDevolucion(String documentoUsuario, String isbnLibro) {

        boolean devolucionOk = GestorPrestamo.devolverPorDocumentoIsbn(
                documentoUsuario, isbnLibro);

        if (devolucionOk == true) {
            Libro libro = gestorLibro.buscarLibroObjeto(isbnLibro);

            if (libro != null) {
                libro.actualizarDisponibilidad("Disponible");
            }

            return true;
        }

        return false;
    }

    public void consultarPrestamosActivos() {
        GestorPrestamo.PrestamosActivos();
    }

    public void consultarMisPrestamos(String documentoUsuario) {
        GestorPrestamo.PrestamosActivosPorUsuario(documentoUsuario);
    }
}