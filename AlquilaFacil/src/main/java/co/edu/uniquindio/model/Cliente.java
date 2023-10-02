package co.edu.uniquindio.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private String ciudad;
    private String direccion;
}