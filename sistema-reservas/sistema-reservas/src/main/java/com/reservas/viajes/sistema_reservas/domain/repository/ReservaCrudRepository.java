package com.reservas.viajes.sistema_reservas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservas.viajes.sistema_reservas.domain.entity.Reserva;

@Repository
public interface ReservaCrudRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByClienteId(Long clienteId);
}
