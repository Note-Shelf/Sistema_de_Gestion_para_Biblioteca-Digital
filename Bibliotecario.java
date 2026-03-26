public class Bibliotecario extends Usuario {

    public Bibliotecario(String documento, String nombreCompleto, String password) {
        super(documento, nombreCompleto, password);
    }

    @Override
    public String getTipoUsuario() {
        return "BIBLIOTECARIO";
    }

    @Override
    public String obtenerDescripcionRol() {
        return "BIBLIOTECARIO: Gestiona libros y prestamos."
                + " Solo puede consultar Usuarios.";
    }
}