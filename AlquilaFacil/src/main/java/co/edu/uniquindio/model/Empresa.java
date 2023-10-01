package co.edu.uniquindio.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class Empresa {

    private String nombre;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Vehiculo> listaVehiculos;
    private ArrayList<Registro> listaRegistros;
    private static final Logger LOGGER = Logger.getLogger(Empresa.class.getName());

    public Empresa(String nombre) {
        LOGGER.log(Level.INFO, "Se crea la instancia de la empresa");
        this.nombre = nombre;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaVehiculos = new ArrayList<Vehiculo>();
        this.listaRegistros = new ArrayList<Registro>();
    }

    public Empresa() {

    }
}
