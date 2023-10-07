package co.edu.uniquindio.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehiculo implements Serializable {
    private String placa;
    private String referencia;
    private String registro;
    private MarcaVehiculo marcaVehiculo;
    private String modelo;
    private String imagenVehiculo;
    private double kilometraje;
    private double precioAlquiler; //Es el precio de alquiler por dia
    private TipoCajaVehiculo tipoCajaVehiculo;
    private int numSillas;
}
