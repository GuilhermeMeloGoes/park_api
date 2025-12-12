package com.guilherme.park_api.controller;

import com.guilherme.park_api.entity.Usuario;
import com.guilherme.park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuarioRecuperadoPorId = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioRecuperadoPorId);
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarSenhaUsuarioPorId(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.editarSenhaPorId(id, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

}
