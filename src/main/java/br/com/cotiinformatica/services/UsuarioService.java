package br.com.cotiinformatica.services;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public void saveOrUpdate(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) throws Exception {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findByEmailAndSenha(String email, String senha) throws Exception {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

}
