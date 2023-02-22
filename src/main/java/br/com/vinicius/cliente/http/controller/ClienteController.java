package br.com.vinicius.cliente.http.controller;

import br.com.vinicius.cliente.entities.Cliente;
import br.com.vinicius.cliente.http.controller.dto.filtro.ClienteFiltro;
import br.com.vinicius.cliente.services.impl.ClienteServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Cliente> listarClientes(ClienteFiltro clienteFiltro, Pageable pageable) {
        return clienteService.listarClientes(clienteFiltro, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarClientePorId(@PathVariable("id") UUID id) {
        return clienteService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCliente(@PathVariable("id") UUID id) {
        clienteService.buscarPorId(id)
                .map(cliente -> {
                    clienteService.removerPorId(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaCliente(@PathVariable("id") UUID id, @RequestBody Cliente cliente) {
        clienteService.buscarPorId(id)
                .map(clienteBase -> {
                    modelMapper.map(cliente, clienteBase);
                    clienteService.salvar(clienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

}
