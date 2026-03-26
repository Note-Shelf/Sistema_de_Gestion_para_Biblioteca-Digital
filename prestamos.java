import java.time.LocalDate;

public class prestamos {

    String id;
    String isbnLibro;
    String documentoUsuario;
    String fechaPrestamo;
    boolean activo;

    public prestamos( String id, String isbnLibro, String documentoUsuario){
        this.id = id;
        this.isbnLibro = isbnLibro;
        this.documentoUsuario = documentoUsuario;
        this.fechaPrestamo = LocalDate.now() . toString();
        this.activo = true;
    }

    public void devolucionLibro(){
     this.activo = false;   
    }
    
}