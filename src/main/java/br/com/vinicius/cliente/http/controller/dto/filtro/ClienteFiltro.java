package br.com.vinicius.cliente.http.controller.dto.filtro;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltro {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
}
