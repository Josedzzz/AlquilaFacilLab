package co.edu.uniquindio.utilities;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase singelton para acceder a los textos que estan guardados en properties
 */
public class Propiedades {
    private static Propiedades instancia;
    @Getter
    @Setter
    private ResourceBundle resourceBundle;
    @Getter
    @Setter
    private String idioma;

    private Propiedades(){
        idioma = leerIdioma();
        this.resourceBundle = ResourceBundle.getBundle("textos", new Locale(idioma));
    }

    public static Propiedades getInstance(){
        if(instancia == null){
            instancia = new Propiedades();
        }
        return instancia;
    }

    public String leerIdioma() {
        if (idioma == null || idioma.equals("")) {
            idioma = "en";
        }
        return this.getIdioma();
    }

    public void escribirIdioma(String idioma){
        this.setIdioma(idioma);
    }

}
