package com.reservas.viajes.sistema_reservas.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ViajeDTO {

    private Long id;
    private String destino;
    private int duracion;
    private double precio;
    private String descripcion;

}
