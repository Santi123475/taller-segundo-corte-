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

import com.reservas.viajes.sistema_reservas.domain.dto.ClienteDTO;
import com.reservas.viajes.sistema_reservas.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<ClienteDTO> clientes = clienteService.obtenerTodos();
        return ResponseEntity.ok().body(clientes);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<ClienteDTO> cliente = clienteService.obtenerPorId(id);
        return cliente.isPresent() 
                ? ResponseEntity.ok().body(cliente.get()) 
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    // Guardar nuevo cliente
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoCliente = clienteService.guardar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteDTO> clienteActualizado = clienteService.actualizar(id, clienteDTO);
        return clienteActualizado.isPresent() 
                ? ResponseEntity.ok().body(clienteActualizado.get()) 
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return clienteService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }
}
