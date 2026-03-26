public class Administrador extends Usuario {

    public Administrador(String documento, String nombreCompleto, String password) {
        super(documento, nombreCompleto, password);
    }

    @Override
    public String getTipoUsuario() {
        return "ADMINISTRADOR";
    }

    @Override
    public String obtenerDescripcionRol() {
        return "ADMINISTRADOR: Acceso total. Puede gestionar"
                + " libros, Usuarios y prestamos.";
    }
}