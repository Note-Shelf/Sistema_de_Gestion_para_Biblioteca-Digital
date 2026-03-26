public class Validador {

    public static boolean textosSonIguales(String texto1, String texto2) {

        if (texto1.length() != texto2.length()) {
            return false;
        }

        char[] letras1 = texto1.toCharArray();
        char[] letras2 = texto2.toCharArray();

        for (int i = 0; i < letras1.length; i++) {
            if (letras1[i] != letras2[i]) {
                return false;
            }
        }

        return true;
    }

    public static boolean textoEstaVacio(String texto) {

        String textoSinEspacios = texto.replaceAll("\\s+", "");

        if (textoSinEspacios.length() == 0) {
            return true;
        }

        return false;
    }

    public static boolean ContraseñaEsSegura(String Contraseña) {

        if (Contraseña.length() >= 6) {
            return true;
        }

        return false;
    }
}