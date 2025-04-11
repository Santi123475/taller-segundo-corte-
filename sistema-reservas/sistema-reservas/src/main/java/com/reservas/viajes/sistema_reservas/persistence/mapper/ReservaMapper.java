package com.reservas.viajes.sistema_reservas.persistence.mapper;

import org.springframework.stereotype.Component;

import com.reservas.viajes.sistema_reservas.domain.dto.ReservaDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Cliente;
import com.reservas.viajes.sistema_reservas.domain.entity.Reserva;
import com.reservas.viajes.sistema_reservas.domain.entity.Viaje;

@Component
public class ReservaMapper {

    public ReservaDTO toDTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getId(),
                reserva.getFechaReserva(),
                reserva.getEstado(),
                reserva.getViaje().getId(),
                reserva.getCliente().getId()
        );
    }

    public Reserva toEntity(ReservaDTO reservaDTO, Viaje viaje, Cliente cliente) {
        return new Reserva(
                reservaDTO.getId(),
                reservaDTO.getFechaReserva(),
                reservaDTO.getEstado(),
                viaje,
                cliente
        );
    }
}
