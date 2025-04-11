package com.reservas.viajes.sistema_reservas.domain.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.reservas.viajes.sistema_reservas.domain.entity.Pago;

public interface PagoCrudRepository extends CrudRepository<Pago, Long> {
    List<Pago> findByReservaId(Long reservaId);
}