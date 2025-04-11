package com.reservas.viajes.sistema_reservas.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.reservas.viajes.sistema_reservas.domain.dto.PagoDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Pago;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mappings({
        @Mapping(source = "reserva.id", target = "reservaId")
    })
    PagoDTO toDTO(Pago pago);

    @Mappings({
        @Mapping(source = "reservaId", target = "reserva.id")
    })
    Pago toEntity(PagoDTO pagoDTO);
}
