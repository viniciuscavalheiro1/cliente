package br.com.vinicius.cliente.services.impl;

import br.com.vinicius.cliente.entities.Cliente;
import br.com.vinicius.cliente.http.controller.dto.filtro.ClienteFiltro;
import br.com.vinicius.cliente.repositories.ClienteRepository;
import br.com.vinicius.cliente.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Page<Cliente> listarClientes(ClienteFiltro clienteFiltro, Pageable pageable) {
        Cliente cliente = Cliente.builder()
                .id(clienteFiltro.getId())
                .nome(clienteFiltro.getNome())
                .email(clienteFiltro.getEmail())
                .cpf(clienteFiltro.getCpf())
                .build();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(cliente, exampleMatcher);

        return clienteRepository.findAll(example, pageable);
    }

    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteRepository.findById(id);
    }

    public void removerPorId(UUID id) {
        clienteRepository.deleteById(id);
    }
}
