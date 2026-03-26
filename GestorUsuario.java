import java.util.ArrayList;

public class GestorUsuario {

    private ArrayList<Usuario> lista;

    public GestorUsuario() {

        lista = new ArrayList<>();

        lista.add(new Administrador("admin", "Administrador Principal", "admin123"));

        lista.add(new Bibliotecario("biblio", "Bibliotecario Principal", "biblio123"));

        lista.add(new Lector("lector", "Lector Principal", "lector123"));
    }

    public Usuario iniciarSesion(String documento, String Contraseña) {

        boolean documentoVacio = Validador.textoEstaVacio(documento);
        if (documentoVacio == true) {
            return null;
        }

        boolean ContraseñaVacia = Validador.textoEstaVacio(Contraseña);
        if (ContraseñaVacia == true) {
            return null;
        }

        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);

            boolean documentoCorrecto = Validador.textosSonIguales(u.getDocumento(), documento);

            if (documentoCorrecto == true) {
                boolean claveCorrecta = u.autenticar(Contraseña);

                if (claveCorrecta == true) {
                    return u;
                }
            }
        }

        return null;
    }

    public boolean registrarUsuario(String documento, String nombreCompleto,
            String tipoStr, String Contraseña) {

        boolean documentoVacio = Validador.textoEstaVacio(documento);
        if (documentoVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el documento no puede estar vacio.");
            System.out.println("____________________________________");
            return false;
        }

        boolean nombreVacio = Validador.textoEstaVacio(nombreCompleto);
        if (nombreVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el nombre no puede estar vacio.");
            System.out.println("____________________________________");
            return false;
        }

        boolean ContraseñaVacia = Validador.textoEstaVacio(Contraseña);
        if (ContraseñaVacia == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: la Contraseña no puede estar vacia.");
            System.out.println("____________________________________");
            return false;
        }

        boolean ContraseñaSegura = Validador.ContraseñaEsSegura(Contraseña);
        if (ContraseñaSegura == false) {
            System.out.println("____________________________________");
            System.out.println("ERROR: la Contraseña es muy corta.");
            System.out.println("Debe tener al menos 6 caracteres.");
            System.out.println("Ejemplo: segura7, clave99");
            System.out.println("____________________________________");
            return false;
        }

        String tipoMinusculas = tipoStr.toLowerCase();

        boolean esAdmin = Validador.textosSonIguales(tipoMinusculas, "administrador");
        boolean esBiblio = Validador.textosSonIguales(tipoMinusculas, "bibliotecario");
        boolean esLector = Validador.textosSonIguales(tipoMinusculas, "lector");

        boolean tipoValido = false;
        if (esAdmin == true) {
            tipoValido = true;
        }
        if (esBiblio == true) {
            tipoValido = true;
        }
        if (esLector == true) {
            tipoValido = true;
        }

        if (tipoValido == false) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el tipo de Usuario no es valido.");
            System.out.println("Los tipos permitidos son:");
            System.out.println("  administrador");
            System.out.println("  bibliotecario");
            System.out.println("  lector");
            System.out.println("____________________________________");
            return false;
        }

        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            boolean documentoRepetido = Validador.textosSonIguales(u.getDocumento(), documento);

            if (documentoRepetido == true) {
                System.out.println("____________________________________");
                System.out.println("ERROR: ya existe Usuario con documento: " + documento);
                System.out.println("Cada Usuario debe tener un documento unico.");
                System.out.println("____________________________________");
                return false;
            }
        }

        Usuario nuevo = null;

        if (esAdmin == true) {
            nuevo = new Administrador(documento, nombreCompleto, Contraseña);
        }
        if (esBiblio == true) {
            nuevo = new Bibliotecario(documento, nombreCompleto, Contraseña);
        }
        if (esLector == true) {
            nuevo = new Lector(documento, nombreCompleto, Contraseña);
        }

        lista.add(nuevo);
        return true;
    }

    public void listarUsuarios() {

        if (lista.size() == 0) {
            System.out.println("____________________________________");
            System.out.println("No hay Usuarios registrados.");
            System.out.println("____________________________________");
            return;
        }

        System.out.println("____________________________________");
        System.out.println("LISTA DE UsuarioS (" + lista.size() + " en total)");
        System.out.println("------------------------------------");

        for (int i = 0; i < lista.size(); i++) {

            System.out.println((i + 1) + ". " + lista.get(i).toString());
        }

        System.out.println("____________________________________");
    }

    public void buscarUsuarioPorDocumento(String documento) {

        boolean documentoVacio = Validador.textoEstaVacio(documento);
        if (documentoVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el documento no puede estar vacio.");
            System.out.println("____________________________________");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            boolean encontrado = Validador.textosSonIguales(u.getDocumento(), documento);

            if (encontrado == true) {
                System.out.println("____________________________________");
                System.out.println("Usuario ENCONTRADO");
                System.out.println("------------------------------------");
                System.out.println(u.toString());
                System.out.println("Rol: " + u.obtenerDescripcionRol());
                System.out.println("____________________________________");
                return;
            }
        }

        System.out.println("____________________________________");
        System.out.println("No se encontro Usuario con documento: " + documento);
        System.out.println("____________________________________");
    }

    public boolean eliminarUsuario(String documento, Usuario operador,
            GestorPrestamo GestorPrestamo) {

        boolean documentoVacio = Validador.textoEstaVacio(documento);
        if (documentoVacio == true) {
            System.out.println("____________________________________");
            System.out.println("ERROR: el documento no puede estar vacio.");
            System.out.println("____________________________________");
            return false;
        }

        boolean esSuCuenta = Validador.textosSonIguales(documento, operador.getDocumento());
        if (esSuCuenta == true) {
            System.out.println("____________________________________");
            System.out.println("ACCION DENEGADA: no puedes eliminar");
            System.out.println("tu propia cuenta con sesion activa.");
            System.out.println("____________________________________");
            return false;
        }

        Usuario UsuarioAEliminar = buscarUsuarioObjeto(documento);

        if (UsuarioAEliminar == null) {
            System.out.println("____________________________________");
            System.out.println("No se encontro Usuario con documento: " + documento);
            System.out.println("____________________________________");
            return false;
        }

        boolean esAdmin = Validador.textosSonIguales(
                UsuarioAEliminar.getTipoUsuario(), "ADMINISTRADOR");

        if (esAdmin == true) {
            int cantidadAdmins = contarAdministradores();

            if (cantidadAdmins <= 1) {
                System.out.println("____________________________________");
                System.out.println("ACCION DENEGADA: no se puede eliminar");
                System.out.println("al unico administrador del sistema.");
                System.out.println("Primero registre otro administrador.");
                System.out.println("____________________________________");
                return false;
            }
        }

        boolean tienePrestamos = GestorPrestamo.UsuarioTienePrestamosActivos(documento);
        if (tienePrestamos == true) {
            System.out.println("____________________________________");
            System.out.println("ACCION DENEGADA: el Usuario tiene");
            System.out.println("prestamos activos sin devolver.");
            System.out.println("Primero debe devolver todos sus libros.");
            System.out.println("____________________________________");
            return false;
        }

        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            boolean mismoDocumento = Validador.textosSonIguales(u.getDocumento(), documento);

            if (mismoDocumento == true) {
                lista.remove(i);
                return true;
            }
        }

        return false;
    }

    public Usuario buscarUsuarioObjeto(String documento) {

        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            boolean mismoDocumento = Validador.textosSonIguales(u.getDocumento(), documento);

            if (mismoDocumento == true) {
                return u;
            }
        }

        return null;
    }

    private int contarAdministradores() {

        int contador = 0;

        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            boolean esAdmin = Validador.textosSonIguales(u.getTipoUsuario(), "ADMINISTRADOR");

            if (esAdmin == true) {
                contador++;
            }
        }

        return contador;
    }
}