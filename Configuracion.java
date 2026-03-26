import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Configuracion {

    private String nombreBiblioteca;
    private String version;
    private int maxIntentosLogin;
    private int maxPrestamos;

    public Configuracion() {

        nombreBiblioteca = "Biblioteca Digital SENA 2026";
        version = "1.0";
        maxIntentosLogin = 3;
        maxPrestamos = 3;

        cargarArchivo();
    }

    private void cargarArchivo() {

        Properties propiedades = new Properties();

        try {
            FileInputStream archivo = new FileInputStream("config.properties");
            propiedades.load(archivo);
            archivo.close();

            if (propiedades.containsKey("nombreBiblioteca")) {
                nombreBiblioteca = propiedades.getProperty("nombreBiblioteca");
            }
            if (propiedades.containsKey("version")) {
                version = propiedades.getProperty("version");
            }
            if (propiedades.containsKey("maxIntentosLogin")) {
                maxIntentosLogin = Integer.parseInt(propiedades.getProperty("maxIntentosLogin"));
            }
            if (propiedades.containsKey("maxPrestamos")) {
                maxPrestamos = Integer.parseInt(propiedades.getProperty("maxPrestamos"));
            }

            System.out.println("____________________________________");
            System.out.println("Configuracion cargada desde archivo.");
            System.out.println("____________________________________");

        } catch (FileNotFoundException e) {
            System.out.println("____________________________________");
            System.out.println("AVISO: no se encontro config.properties.");
            System.out.println("Se usaran los valores por defecto.");
            System.out.println("____________________________________");

        } catch (IOException e) {
            System.out.println("____________________________________");
            System.out.println("AVISO: no se pudo leer config.properties.");
            System.out.println("Se usaran los valores por defecto.");
            System.out.println("____________________________________");

        } catch (NumberFormatException e) {
            System.out.println("____________________________________");
            System.out.println("AVISO: un numero en config.properties esta mal escrito.");
            System.out.println("Se usaran los valores por defecto.");
            System.out.println("____________________________________");
        }
    }

    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public String getVersion() {
        return version;
    }

    public int getMaxIntentosLogin() {
        return maxIntentosLogin;
    }

    public int getMaxPrestamos() {
        return maxPrestamos;
    }
}