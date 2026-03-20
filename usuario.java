public class usuario {

    public enum TipoUsuario {
        ADMINISTRADOR,
        BIBLIOTECARIO,
        LECTOR
    }

    private String documento;
    private String nombreCompleto;
    private TipoUsuario tipoUsuario;
    private String password;

    public usuario(String documento, String nombreCompleto, TipoUsuario tipoUsuario, String password) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento es un campo obligatorio.");
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es un campo obligatorio.");
        }
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("El tipo de usuario es obligatorio y debe ser válido.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es un campo obligatorio.");
        }

        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public boolean autenticar(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public boolean verificarRol(TipoUsuario rolRequerido) {
        return this.tipoUsuario == TipoUsuario.ADMINISTRADOR || this.tipoUsuario == rolRequerido;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto != null && !nombreCompleto.trim().isEmpty()) {
            this.nombreCompleto = nombreCompleto;
        } else {
            throw new IllegalArgumentException("El nombre no puede quedar vacío.");
        }
    }

    public void setPassword(String nuevaPassword) {
        if (nuevaPassword != null && !nuevaPassword.trim().isEmpty()) {
            this.password = nuevaPassword;
        } else {
            throw new IllegalArgumentException("La contraseña no puede quedar vacía.");
        }
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        if (tipoUsuario != null) {
            this.tipoUsuario = tipoUsuario;
        } else {
            throw new IllegalArgumentException("El tipo de usuario no puede ser nulo.");
        }
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "Documento='" + documento + '\'' +
                ", Nombre Completo='" + nombreCompleto + '\'' +
                ", Tipo='" + tipoUsuario + '\'' +
                '}';
    }
}
