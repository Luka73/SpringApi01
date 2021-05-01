package br.com.cotiinformatica.adapters;


import br.com.cotiinformatica.dtos.ClientePostDTO;
import br.com.cotiinformatica.dtos.UsuarioPostDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Usuario;

/*
 * Classe para transferencia de dados
 * de objetos DTO para Entidade
 */
public class DTOEntityAdapter {

    // ClientePostDTO -> Cliente
    public static Cliente getCliente(ClientePostDTO dto) {

        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());

        return cliente;
    }

    //UsuarioPostDTo -> Usuario
    public static Usuario getUsuario(UsuarioPostDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }

}
