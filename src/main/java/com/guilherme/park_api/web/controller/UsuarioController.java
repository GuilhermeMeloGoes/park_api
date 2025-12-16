package com.guilherme.park_api.web.controller;

import com.guilherme.park_api.entity.Usuario;
import com.guilherme.park_api.service.UsuarioService;
import com.guilherme.park_api.web.controller.dto.UsuarioCreateDto;
import com.guilherme.park_api.web.controller.dto.UsuarioResponseDto;
import com.guilherme.park_api.web.controller.dto.UsuarioSenhaDto;
import com.guilherme.park_api.web.controller.dto.mapper.UsuarioMapper;
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
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(@RequestBody UsuarioCreateDto createDto) {
        Usuario usuarioCriado = usuarioService.cadastrarUsuario(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toUsuarioResponseDto(usuarioCriado));
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuarioRecuperadoPorId = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toUsuarioResponseDto(usuarioRecuperadoPorId));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarSenhaUsuarioPorId(@PathVariable Long id, @RequestBody UsuarioSenhaDto usuarioSenhaDto) {
        Usuario usuarioAtualizado = usuarioService.editarSenhaPorId(id, usuarioSenhaDto.getSenhaAtual(), usuarioSenhaDto.getNovaSenha(), usuarioSenhaDto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioResponseDto>> buscarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.ok(UsuarioMapper.toListUsuarioResponseDto(usuarios));
    }

}
