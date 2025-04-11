package com.reservas.viajes.sistema_reservas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;

}
