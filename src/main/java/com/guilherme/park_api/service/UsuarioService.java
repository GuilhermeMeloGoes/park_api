package com.guilherme.park_api.service;

import com.guilherme.park_api.entity.Usuario;
import com.guilherme.park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Transactional
    public Usuario editarSenhaPorId(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova senha não confere com a confirmação de senha.");
        }

        if (!usuario.getPassword().equals(senhaAtual)) {
            throw new RuntimeException("A senha atual não confere.");
        }

        usuario.setPassword(novaSenha);
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios;
    }
}
