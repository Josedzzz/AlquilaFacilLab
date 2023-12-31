package co.edu.uniquindio.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registro implements Serializable {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDateTime fechaRegistro;
    private LocalDate fechaInicio;
    private LocalDate fechaRegreso;
    private double precioFactura; //Precio total de la alquilada
}
