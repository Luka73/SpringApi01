package br.com.cotiinformatica.controllers;

import br.com.cotiinformatica.adapters.DTOEntityAdapter;
import br.com.cotiinformatica.adapters.EntityDTOAdapter;
import br.com.cotiinformatica.adapters.EntityResponseAdapter;
import br.com.cotiinformatica.dtos.ClienteGetDTO;
import br.com.cotiinformatica.dtos.ClientePutDTO;
import br.com.cotiinformatica.responses.ClienteDeleteResponse;
import br.com.cotiinformatica.responses.ClientePostResponse;
import br.com.cotiinformatica.responses.ClientePutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.cotiinformatica.dtos.ClientePostDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.services.ClienteService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClienteController {

	private static final String RESOURCE = "/api/clientes";

	@Autowired
	private ClienteService clienteService;

	@CrossOrigin // Injeta a configuração feita em CorsConfiguration permitindo acesso de qq origem.
	@RequestMapping(value = RESOURCE, method = RequestMethod.POST) // Método para disponibilizar um serviço de cadastro de cliente na API.
	@ResponseBody // indica que o método irá retornar dados no serviço
	public ResponseEntity<ClientePostResponse> post(@RequestBody ClientePostDTO dto) {

		try {

			Cliente cliente = DTOEntityAdapter.getCliente(dto);
			clienteService.saveOrUpdate(cliente);

			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(EntityResponseAdapter.getPostResponse(cliente));

		} catch (Exception e) {

			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ClientePutResponse> put(@RequestBody ClientePutDTO dto) {

		try {

			// verificar se o cliente existe no banco de dados
			Cliente cliente = clienteService.findById(dto.getIdCliente());

			if (cliente == null) // se o cliente não for encontrado..
				return ResponseEntity
						.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(null);

			// alterando os dados do cliente
			cliente.setNome(dto.getNome());
			clienteService.saveOrUpdate(cliente);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(EntityResponseAdapter.getPutResponse(cliente));

		} catch (Exception e) {

			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE + "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ClienteDeleteResponse> delete(@PathVariable("id") Integer id) {

		try {

			// verificar se o cliente existe no banco de dados
			Cliente cliente = clienteService.findById(id);

			if (cliente == null) // se o cliente não for encontrado..
				return ResponseEntity
						.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(null);

			// excluindo o cliente..
			clienteService.delete(id);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(EntityResponseAdapter.getDeleteResponse(cliente));

		} catch (Exception e) {

			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ClienteGetDTO>> getAll() {

		try {

			List<ClienteGetDTO> result = new ArrayList<>();

			// percorrendo os clientes do banco de dados
			for (Cliente cliente : clienteService.findAll()) {
				result.add(EntityDTOAdapter.getDTO(cliente));
			}

			if(result.size() == 0)
				return ResponseEntity
						.status(HttpStatus.NO_CONTENT)
						.body(null);
			else
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(result);

		} catch (Exception e) {

			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}

	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE + "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ClienteGetDTO> getById(@PathVariable("id") Integer id) {

		try {

			// buscar o cliente no banco de dados atraves do ID..
			Cliente cliente = clienteService.findById(id);

			if(cliente == null)
				return ResponseEntity
						.status(HttpStatus.NO_CONTENT)
						.body(null);
			else
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(EntityDTOAdapter.getDTO(cliente));

		} catch (Exception e) {

			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}

	}

}
