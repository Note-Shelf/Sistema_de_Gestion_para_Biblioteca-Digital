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

        boolean isbnVacio = Validador.textoEstaVacio(isbn);
        if (isbnVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el ISBN no puede estar vacio.");
            System.out.println("Ejemplo de ISBN: 978-84-376-0494-7");
            System.out.println("____________________________________");
            return false;
        }

        boolean tituloVacio = Validador.textoEstaVacio(titulo);
        if (tituloVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el titulo no puede estar vacio.");
            System.out.println("____________________________________");
            return false;
        }

        boolean autorVacio = Validador.textoEstaVacio(autor);
        if (autorVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el nombre del autor no puede estar vacio.");
            System.out.println("____________________________________");
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