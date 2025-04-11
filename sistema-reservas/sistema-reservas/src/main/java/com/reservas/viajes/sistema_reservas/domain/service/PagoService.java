package com.reservas.viajes.sistema_reservas.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.viajes.sistema_reservas.domain.dto.PagoDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Pago;
import com.reservas.viajes.sistema_reservas.domain.repository.PagoCrudRepository;
import com.reservas.viajes.sistema_reservas.persistence.mapper.PagoMapper;

@Service
public class PagoService {

    @Autowired
    private PagoCrudRepository pagoCrudRepository;

    @Autowired
    private PagoMapper pagoMapper;

    public List<PagoDTO> listarPagos() {
        List<PagoDTO> pagosDTO = new ArrayList<>();
        pagoCrudRepository.findAll().forEach(pago -> pagosDTO.add(pagoMapper.toDTO(pago)));
        return pagosDTO;
    }

    public Optional<PagoDTO> obtenerPagoPorId(Long id) {
        return pagoCrudRepository.findById(id).map(pagoMapper::toDTO);
    }

    public List<PagoDTO> obtenerPagosPorReserva(Long reservaId) {
        List<PagoDTO> pagosDTO = new ArrayList<>();
        pagoCrudRepository.findByReservaId(reservaId).forEach(pago -> pagosDTO.add(pagoMapper.toDTO(pago)));
        return pagosDTO;
    }

    public PagoDTO guardarPago(PagoDTO pagoDTO) {
        Pago pago = pagoMapper.toEntity(pagoDTO);
        return pagoMapper.toDTO(pagoCrudRepository.save(pago));
    }

    public Optional<PagoDTO> actualizarPago(Long id, PagoDTO pagoDTO) {
        return pagoCrudRepository.findById(id).map(pagoExistente -> {
            pagoExistente.setMetodoPago(pagoDTO.getMetodoPago());
            pagoExistente.setMonto(pagoDTO.getMonto());
            pagoExistente.setFecha(pagoDTO.getFecha());
            pagoExistente.setEstado(pagoDTO.getEstado());
            pagoExistente.setNumeroTransaccion(pagoDTO.getNumeroTransaccion());
            return pagoMapper.toDTO(pagoCrudRepository.save(pagoExistente));
        });
    }

    public boolean eliminarPago(Long id) {
        return pagoCrudRepository.findById(id).map(pago -> {
            pagoCrudRepository.delete(pago);
            return true;
        }).orElse(false);
    }
}
