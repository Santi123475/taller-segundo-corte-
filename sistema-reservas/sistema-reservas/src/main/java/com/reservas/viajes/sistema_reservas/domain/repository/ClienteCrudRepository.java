package com.reservas.viajes.sistema_reservas.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.reservas.viajes.sistema_reservas.domain.entity.Cliente;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findByEmailIgnoreCase(String email);

    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

    Optional<Cliente> findByTelefono(String telefono);

    List<Cliente> findByTelefonoContaining(String telefono);
}
