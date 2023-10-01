package co.edu.uniquindio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    private String placa;
    private String referencia;
    private MarcaVehiculo marcaVehiculo;
    private String modelo;
    private String rutaImagen;
    private double kilometraje;
    private double precioAlquiler; //Es el precio de alquiler por dia
    private TipoCajaVehiculo tipoCajaVehiculo;
    private int numSillas;
}
