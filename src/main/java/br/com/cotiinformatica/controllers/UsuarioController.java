package br.com.cotiinformatica.controllers;

import br.com.cotiinformatica.adapters.DTOEntityAdapter;
import br.com.cotiinformatica.adapters.EntityResponseAdapter;
import br.com.cotiinformatica.dtos.UsuarioPostDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.responses.UsuarioPostResponse;
import br.com.cotiinformatica.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private static final String RESOURCE = "/api/usuarios";

    @Autowired
    private UsuarioService usuarioService;

    // Método para disponibilizar um serviço de cadastro de usuario na API.
    @CrossOrigin
    @RequestMapping(value = RESOURCE, method = RequestMethod.POST)
    @ResponseBody // indica que o método irá retornar dados no serviço
    public ResponseEntity<UsuarioPostResponse> post(@RequestBody UsuarioPostDTO dto) {

        try{
            Usuario usuario = DTOEntityAdapter.getUsuario(dto);

            if(!dto.getSenha().equals(dto.getSenhaConfirmacao())) {
                System.out.println("Senhas não conferem");
                throw new IllegalArgumentException();
            } else if (usuarioService.findByEmail(dto.getEmail()) != null) {
                System.out.println("Email já cadastrado no banco de dados");
                throw new IllegalArgumentException();
            }

            usuarioService.saveOrUpdate(usuario);
            return ResponseEntity.status(HttpStatus.CREATED) // 201 - Created !
                    .body(EntityResponseAdapter.getUsuarioPostResponse(usuario));


        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) // 400 - validação
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
