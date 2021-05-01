package br.com.cotiinformatica.adapters;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.responses.ClienteDeleteResponse;
import br.com.cotiinformatica.responses.ClientePostResponse;
import br.com.cotiinformatica.responses.ClientePutResponse;
import br.com.cotiinformatica.responses.UsuarioPostResponse;

import java.util.Date;

public class EntityResponseAdapter {

    public static ClientePostResponse getPostResponse(Cliente cliente) {

        ClientePostResponse response = new ClientePostResponse();

        response.setIdCliente(cliente.getIdCliente());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setCpf(cliente.getCpf());
        response.setDataCadastro(new Date());

        return response;
    }

    public static ClientePutResponse getPutResponse(Cliente cliente) {

        ClientePutResponse response = new ClientePutResponse();

        response.setIdCliente(cliente.getIdCliente());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setCpf(cliente.getCpf());
        response.setDataAlteracao(new Date());

        return response;
    }

    public static ClienteDeleteResponse getDeleteResponse(Cliente cliente) {

        ClienteDeleteResponse response = new ClienteDeleteResponse();

        response.setIdCliente(cliente.getIdCliente());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setCpf(cliente.getCpf());
        response.setDataExclusao(new Date());

        return response;
    }

    public static UsuarioPostResponse getUsuarioPostResponse(Usuario usuario) {
        UsuarioPostResponse response = new UsuarioPostResponse();

        response.setIdUsuario(usuario.getIdUsuario());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());

        return response;
    }

}
