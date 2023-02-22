package br.com.vinicius.cliente.repositories;

import br.com.vinicius.cliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
