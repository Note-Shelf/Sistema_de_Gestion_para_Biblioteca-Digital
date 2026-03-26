public class Lector extends Usuario {

    public Lector(String documento, String nombreCompleto, String password) {
        super(documento, nombreCompleto, password);
    }

    @Override
    public String getTipoUsuario() {
        return "LECTOR";
    }

    @Override
    public String obtenerDescripcionRol() {
        return "LECTOR: Puede consultar libros y"
                + " solicitar prestamos propios.";
    }
}