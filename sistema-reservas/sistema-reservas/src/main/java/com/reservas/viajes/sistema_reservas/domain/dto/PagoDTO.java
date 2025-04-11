
package com.reservas.viajes.sistema_reservas.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long id;
    private String metodoPago;
    private Double monto;
    private LocalDate fecha;
    private String estado;
    private String numeroTransaccion;
    private Long reservaId;
}
