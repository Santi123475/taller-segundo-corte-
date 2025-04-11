package com.reservas.viajes.sistema_reservas.domain.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservaDTO {

    private Long id;
    private LocalDate fechaReserva;
    private String estado;
    private Long viajeId;
    private Long clienteId;

}
