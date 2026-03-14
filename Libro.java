public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String estado;

    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.estado = "Disponible";
    }

    public boolean validarCampos() {

        int cantidadLetrasIsbn = isbn.length();

        if (cantidadLetrasIsbn == 0) {
            System.out.println("Error: el ISBN no puede estar vacio.");
            return false;
        }

        int cantidadLetrasTitulo = titulo.length();

        if (cantidadLetrasTitulo == 0) {
            System.out.println("Error: el titulo no puede estar vacio.");
            return false;
        }

        int cantidadLetrasAutor = autor.length();

        if (cantidadLetrasAutor == 0) {
            System.out.println("Error: el autor no puede estar vacio.");
            return false;
        }

        return true;
    }

    public void actualizarDisponibilidad(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getEstado() {
        return estado;
    }
}