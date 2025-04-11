package com.reservas.viajes.sistema_reservas.persistence.mapper;

import org.mapstruct.Mapper;

import com.reservas.viajes.sistema_reservas.domain.dto.ViajeDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Viaje;

@Mapper(componentModel = "spring")
public interface ViajeMapper {
    ViajeDTO toDTO(Viaje viaje);
    Viaje toEntity(ViajeDTO viajeDTO);
}
