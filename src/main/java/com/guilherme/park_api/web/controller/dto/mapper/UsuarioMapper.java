package com.guilherme.park_api.web.controller.dto.mapper;

import com.guilherme.park_api.entity.Usuario;
import com.guilherme.park_api.web.controller.dto.UsuarioCreateDto;
import com.guilherme.park_api.web.controller.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toUsuarioResponseDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(props);
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListUsuarioResponseDto(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> toUsuarioResponseDto(usuario)).collect(Collectors.toList());
    }


}
