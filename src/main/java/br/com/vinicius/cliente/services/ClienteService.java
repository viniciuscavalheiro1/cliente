package br.com.vinicius.cliente.services;

import br.com.vinicius.cliente.entities.Cliente;
import br.com.vinicius.cliente.http.controller.dto.filtro.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ClienteService {
    Cliente salvar(Cliente cliente);

    Page<Cliente> listarClientes(ClienteFiltro clienteFiltro, Pageable pageable);

    Optional<Cliente> buscarPorId(UUID id);

    void removerPorId(UUID id);
}
