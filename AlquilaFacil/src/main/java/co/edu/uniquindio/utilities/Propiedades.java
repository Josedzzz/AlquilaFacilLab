package co.edu.uniquindio.utilities;

import lombok.Getter;
import java.util.ResourceBundle;

/**
 * Clase singelton para acceder a los textos que estan guardados en properties
 */
public class Propiedades {
    @Getter
    private final ResourceBundle resourceBundle;
    private static Propiedades instancia;
    private Propiedades(){
        this.resourceBundle = ResourceBundle.getBundle("textos");
    }
    public static Propiedades getInstance(){
        if(instancia == null){
            instancia = new Propiedades();
        }
        return instancia;
    }
}
