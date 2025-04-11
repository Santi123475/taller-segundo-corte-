package com.reservas.viajes.sistema_reservas.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservas.viajes.sistema_reservas.domain.dto.PagoDTO;
import com.reservas.viajes.sistema_reservas.domain.service.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listarPagos() {
        return new ResponseEntity<>(pagoService.listarPagos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPagoPorId(@PathVariable Long id) {
        return pagoService.obtenerPagoPorId(id)
                .map(pago -> new ResponseEntity<>(pago, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorReserva(@PathVariable Long reservaId) {
        return new ResponseEntity<>(pagoService.obtenerPagosPorReserva(reservaId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagoDTO> guardarPago(@RequestBody PagoDTO pagoDTO) {
        return new ResponseEntity<>(pagoService.guardarPago(pagoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizarPago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        return pagoService.actualizarPago(id, pagoDTO)
                .map(pago -> new ResponseEntity<>(pago, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        return pagoService.eliminarPago(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}