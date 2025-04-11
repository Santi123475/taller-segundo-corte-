package com.reservas.viajes.sistema_reservas.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.reservas.viajes.sistema_reservas.domain.entity.Viaje;

public interface ViajeCrudRepository extends CrudRepository<Viaje, Long> {
    
    List<Viaje> findByDestinoIgnoreCase(String destino);

    List<Viaje> findByDestinoContainingIgnoreCase(String destino);

    List<Viaje> findByPrecioLessThanEqual(Double precio);

    List<Viaje> findByDuracionGreaterThanEqual(Integer duracion);

    List<Viaje> findByDestino(String destino);
}
