package com.reservas.viajes.sistema_reservas.domain.controllers;

import java.util.List;
import java.util.Optional;

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

import com.reservas.viajes.sistema_reservas.domain.dto.ViajeDTO;
import com.reservas.viajes.sistema_reservas.domain.service.ViajeService;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosViajes() {
        List<ViajeDTO> viajes = viajeService.obtenerTodosLosViajes();
        return viajes.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay viajes disponibles.")
                : ResponseEntity.ok(viajes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerViajePorId(@PathVariable Long id) {
        Optional<ViajeDTO> viaje = viajeService.obtenerViajePorId(id);
        return viaje.isPresent()
                ? ResponseEntity.ok(viaje.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado con el ID: " + id);
    }

    @PostMapping
    public ResponseEntity<?> crearViaje(@RequestBody ViajeDTO viajeDTO) {
        ViajeDTO nuevoViaje = viajeService.guardarViaje(viajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoViaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarViaje(@PathVariable Long id, @RequestBody ViajeDTO viajeDTO) {
        Optional<ViajeDTO> viajeActualizado = viajeService.actualizarViaje(id, viajeDTO);
        return viajeActualizado.isPresent()
                ? ResponseEntity.ok(viajeActualizado.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar. Viaje no encontrado con el ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarViaje(@PathVariable Long id) {
        return viajeService.eliminarViaje(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar. Viaje no encontrado con el ID: " + id);
    }
}
