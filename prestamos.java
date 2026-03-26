import java.time.LocalDate;

public class Prestamos {

    String id;
    String isbnLibro;
    String documentoUsuario;
    String fechaPrestamo;
    boolean activo;

    public Prestamos(String id, String isbnLibro, String documentoUsuario) {
        this.id = id;
        this.isbnLibro = isbnLibro;
        this.documentoUsuario = documentoUsuario;
        this.fechaPrestamo = LocalDate.now().toString();
        this.activo = true;
    }

    public void devolucionLibro() {
        this.activo = false;
    }
}