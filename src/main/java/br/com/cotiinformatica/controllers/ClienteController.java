package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.ClientePostDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.services.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	// Método para disponibilizar um serviço de cadastro de cliente na API.
	@RequestMapping(value = "/api/clientes", method = RequestMethod.POST)
	@ResponseBody // indica que o método irá retornar dados no serviço
	public String post(@RequestBody ClientePostDTO dto) {

		try {

			Cliente cliente = new Cliente();

			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setCpf(dto.getCpf());

			clienteService.saveOrUpdate(cliente);

			return "Cliente " + cliente.getNome() + ", cadastrado com sucesso.";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
