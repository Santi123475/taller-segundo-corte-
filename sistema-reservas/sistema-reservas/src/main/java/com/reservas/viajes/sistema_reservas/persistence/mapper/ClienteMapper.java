package com.reservas.viajes.sistema_reservas.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.reservas.viajes.sistema_reservas.domain.dto.ClienteDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteDTO clienteDTO);
}