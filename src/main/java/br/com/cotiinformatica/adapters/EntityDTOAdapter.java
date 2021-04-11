package br.com.cotiinformatica.adapters;

import br.com.cotiinformatica.dtos.ClienteGetDTO;
import br.com.cotiinformatica.entities.Cliente;

/*
 * Classe para transferencia de dados
 * de objetos Entidade para DTO
 */
public class EntityDTOAdapter {

    // ClienteGetDTO -> Cliente
    public static ClienteGetDTO getDTO(Cliente cliente) {

        ClienteGetDTO dto = new ClienteGetDTO();

        dto.setIdCliente(cliente.getIdCliente());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setCpf(cliente.getCpf());

        return dto;
    }
}
