package br.com.cotiinformatica.controllers;

import br.com.cotiinformatica.dtos.LoginPostDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.filters.JWTAuthorizationFilter;
import br.com.cotiinformatica.responses.LoginPostResponse;
import br.com.cotiinformatica.services.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    private static final String RESOURCE = "/api/login";

    @Autowired
    private UsuarioService usuarioService;

    // Método para disponibilizar um serviço de cadastro de usuario na API.
    @CrossOrigin
    @RequestMapping(value = RESOURCE, method = RequestMethod.POST)
    @ResponseBody // indica que o método irá retornar dados no serviço
    public ResponseEntity<LoginPostResponse> post(@RequestBody LoginPostDTO dto) {

        try {

            // verificar se o email e senha informados existem no banco de dados..
            Usuario usuario = usuarioService.findByEmailAndSenha(dto.getEmail(), dto.getSenha());

            // verificar se o usuário foi encontrado..
            if (usuario != null) {

                LoginPostResponse response = new LoginPostResponse();
                response.setAccessToken(getJWTToken(usuario.getEmail()));

                return ResponseEntity.status(HttpStatus.OK) // 200 - Sucesso!
                        .body(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    private String getJWTToken(String email) {
        String secretKey = JWTAuthorizationFilter.SECRET;

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.builder()
                .setId("COTI_JWT")
                .setSubject(email)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();

        return token;

    }
}
