
package com.senac.openBarWebPI.service;


import com.senac.openBarWebPI.model.Usuario;
import com.senac.openBarWebPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean autenticar(String nome, String senha) {
        Usuario usuario = usuarioRepository.findByNome(nome);
        return usuario != null && usuario.getSenha().equals(senha);
    }


    public Usuario salvarUsuario(Usuario usuario) {
    if (usuario.getTipo() == null) {
        usuario.setTipo("ATENDENTE"); 
    }
    return usuarioRepository.save(usuario);
}
    public Usuario atualizar(Integer id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }
    
   
}
