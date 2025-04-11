package com.reservas.viajes.sistema_reservas.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.viajes.sistema_reservas.domain.dto.ClienteDTO;
import com.reservas.viajes.sistema_reservas.domain.entity.Cliente;
import com.reservas.viajes.sistema_reservas.domain.repository.ClienteCrudRepository;
import com.reservas.viajes.sistema_reservas.persistence.mapper.ClienteMapper;

@Service
public class ClienteService {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public List<ClienteDTO> obtenerTodos() {
        List<ClienteDTO> clientesDTO = new ArrayList<>();
        clienteCrudRepository.findAll().forEach(cliente -> clientesDTO.add(clienteMapper.toDTO(cliente)));
        return clientesDTO;
    }

    public Optional<ClienteDTO> obtenerPorId(Long id) {
        return clienteCrudRepository.findById(id)
                .map(clienteMapper::toDTO);
    }

    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = clienteCrudRepository.save(cliente);
        return clienteMapper.toDTO(clienteGuardado);
    }

    public Optional<ClienteDTO> actualizar(Long id, ClienteDTO clienteDTO) {
        return clienteCrudRepository.findById(id).map(clienteExistente -> {
            clienteExistente.setNombre(clienteDTO.getNombre());
            clienteExistente.setEmail(clienteDTO.getEmail());
            clienteExistente.setTelefono(clienteDTO.getTelefono());
            clienteCrudRepository.save(clienteExistente);
            return clienteMapper.toDTO(clienteExistente);
        });
    }

    public boolean eliminar(Long id) {
        if (clienteCrudRepository.existsById(id)) {
            clienteCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
