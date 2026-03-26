public abstract class Usuario {

    private String documento;
    private String nombreCompleto;
    private String password;

    public Usuario(String documento, String nombreCompleto, String password) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.password = password;
    }

    public abstract String getTipoUsuario();

    public abstract String obtenerDescripcionRol();

    public boolean autenticar(String inputPassword) {

        boolean ContraseñaCorrecta = Validador.textosSonIguales(this.password, inputPassword);

        if (ContraseñaCorrecta == true) {
            return true;
        }

        return false;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String toString() {
        return "Documento: " + documento
                + " | Nombre: " + nombreCompleto
                + " | Tipo: " + getTipoUsuario();
    }
}