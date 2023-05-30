package com.aatorganicos.aatorganicos.dto.mapper;

import org.springframework.stereotype.Component;

import com.aatorganicos.aatorganicos.dto.UsuarioDto;
import com.aatorganicos.aatorganicos.model.Usuario;

@Component
public class UsuarioMapper {
    
    public UsuarioDto toDTO(Usuario usuario) {
        if(usuario == null) {
            return null;
        }
        return new UsuarioDto(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }

    public Usuario toEntity(UsuarioDto usuarioDto) {
        if(usuarioDto == null) {
            return null;
        }
        
        Usuario usuario = new Usuario();

        if(usuarioDto.Id() != null) {
            usuario.setId(usuarioDto.Id());
        }

        usuario.setLogin(usuarioDto.Login());
        usuario.setSenha(usuarioDto.Senha());

        return usuario;
    }
}