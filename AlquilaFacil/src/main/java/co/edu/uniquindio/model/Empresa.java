package co.edu.uniquindio.model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Empresa {

    @Getter
    private ArrayList<Cliente> listaClientes;
    @Getter
    private ArrayList<Vehiculo> listaVehiculos;
    @Getter
    private ArrayList<Registro> listaRegistros;
    private static final Logger LOGGER = Logger.getLogger(Empresa.class.getName());

    //Variable que tendra la instancia de esta clase
    private static Empresa empresa;

    /**
     * Constructor que debe de ser privado para que ninguna otra clase pueda crear instancias de esta clase
     */
    private Empresa() {
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter( new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log( Level.SEVERE, e.getMessage() );
        }
        LOGGER.log(Level.INFO, "Se crea una nueva instancia de la empresa");
        this.listaClientes = new ArrayList<Cliente>();
        this.listaVehiculos = new ArrayList<Vehiculo>();
        this.listaRegistros = new ArrayList<Registro>();
    }

    /**
     * Metodo que se usara en otras clases que requieran la instancia de empresa
     * @return Instancia de la empresa
     */
    public static Empresa getInstance() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

}
