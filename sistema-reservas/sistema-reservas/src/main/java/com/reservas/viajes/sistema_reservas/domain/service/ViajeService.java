package com.reservas.viajes.sistema_reservas.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.viajes.sistema_reservas.domain.dto.ViajeDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Viaje;
import com.reservas.viajes.sistema_reservas.domain.repository.ViajeCrudRepository;
import com.reservas.viajes.sistema_reservas.persistence.mapper.ViajeMapper;

@Service
public class ViajeService {

    @Autowired
    private ViajeCrudRepository viajeCrudRepository;

    @Autowired
    private ViajeMapper viajeMapper;

    public List<ViajeDTO> obtenerTodosLosViajes() {
        List<Viaje> viajes = (List<Viaje>) viajeCrudRepository.findAll();
        return viajes.stream().map(viajeMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<ViajeDTO> obtenerViajePorId(Long id) {
        return viajeCrudRepository.findById(id).map(viajeMapper::toDTO);
    }

    public ViajeDTO guardarViaje(ViajeDTO viajeDTO) {
        Viaje viaje = viajeMapper.toEntity(viajeDTO);
        return viajeMapper.toDTO(viajeCrudRepository.save(viaje));
    }

    public boolean eliminarViaje(Long id) {
        if (viajeCrudRepository.existsById(id)) {
            viajeCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ViajeDTO> actualizarViaje(Long id, ViajeDTO viajeDTO) {
        if (viajeCrudRepository.existsById(id)) {
            Viaje viaje = viajeMapper.toEntity(viajeDTO);
            viaje.setId(id);
            return Optional.of(viajeMapper.toDTO(viajeCrudRepository.save(viaje)));
        }
        return Optional.empty();
    }
}
