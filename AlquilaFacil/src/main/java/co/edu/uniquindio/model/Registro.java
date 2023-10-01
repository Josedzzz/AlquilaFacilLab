package co.edu.uniquindio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registro {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private double precioFactura; //Precio de alquilada
    private String fechaFactura; //Fecha en la que se genero la factura
    private String fechaInicial;
    private String fechaFinal;
}
