package com.reservas.viajes.sistema_reservas.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservas.viajes.sistema_reservas.domain.dto.ReservaDTO;
import com.reservas.viajes.sistema_reservas.domain.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Crear nueva reserva
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO nuevaReserva = reservaService.guardarReserva(reservaDTO);
        return (nuevaReserva != null)
                ? ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la reserva.");
    }

    // Obtener reservas por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> obtenerReservasPorCliente(@PathVariable Long clienteId) {
        List<ReservaDTO> reservas = reservaService.obtenerReservasPorCliente(clienteId);
        return reservas.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron reservas para el cliente especificado.")
                : ResponseEntity.ok(reservas);
    }

    // Cancelar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable Long id) {
        return reservaService.cancelarReserva(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reserva a cancelar.");
    }
}
