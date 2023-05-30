package com.aatorganicos.aatorganicos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.dto.UsuarioDto;
import com.aatorganicos.aatorganicos.dto.mapper.UsuarioMapper;
import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.repository.IUsuarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(IUsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDto> usuario() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public UsuarioDto usuarioPorId(@NotNull @Positive Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UsuarioDto criarUsuario(@Valid UsuarioDto usuario) {
        return usuarioMapper.toDTO(usuarioRepository.save(usuarioMapper.toEntity(usuario)));
    }

    public UsuarioDto atualizaUsuario(@NotNull @Positive Long id, @Valid @NotNull UsuarioDto usuario) {
        return usuarioRepository.findById(id)
                .map(data -> {
                    data.setLogin(usuario.Login());
                    data.setSenha(usuario.Senha());
                    return usuarioMapper.toDTO(usuarioRepository.save(data));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteUsuario( @NotNull @Positive Long id) {

        usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
    
}
