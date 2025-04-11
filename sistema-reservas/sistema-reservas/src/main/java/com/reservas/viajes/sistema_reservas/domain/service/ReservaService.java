package com.reservas.viajes.sistema_reservas.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.viajes.sistema_reservas.domain.dto.ReservaDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Cliente;
import com.reservas.viajes.sistema_reservas.domain.entity.Reserva;
import com.reservas.viajes.sistema_reservas.domain.entity.Viaje;
import com.reservas.viajes.sistema_reservas.domain.repository.ClienteCrudRepository;
import com.reservas.viajes.sistema_reservas.domain.repository.ReservaCrudRepository;
import com.reservas.viajes.sistema_reservas.domain.repository.ViajeCrudRepository;
import com.reservas.viajes.sistema_reservas.persistence.mapper.ReservaMapper;

@Service
public class ReservaService {

    @Autowired
    private ReservaCrudRepository reservaCrudRepository;

    @Autowired
    private ViajeCrudRepository viajeCrudRepository;

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ReservaMapper reservaMapper;
    public ReservaDTO guardarReserva(ReservaDTO reservaDTO) {
        Optional<Viaje> viajeOpt = viajeCrudRepository.findById(reservaDTO.getViajeId());
        Optional<Cliente> clienteOpt = clienteCrudRepository.findById(reservaDTO.getClienteId());

        if (viajeOpt.isPresent() && clienteOpt.isPresent()) {
            Reserva reserva = reservaMapper.toEntity(reservaDTO, viajeOpt.get(), clienteOpt.get());
            return reservaMapper.toDTO(reservaCrudRepository.save(reserva));
        }

        return null;
    }

    public List<ReservaDTO> obtenerReservasPorCliente(Long clienteId) {
        return reservaCrudRepository.findByClienteId(clienteId).stream()
                .map(reservaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean cancelarReserva(Long id) {
        if (reservaCrudRepository.existsById(id)) {
            reservaCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
