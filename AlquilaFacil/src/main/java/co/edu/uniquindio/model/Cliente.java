package co.edu.uniquindio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private String cuidad;
    private String direccion;

}
