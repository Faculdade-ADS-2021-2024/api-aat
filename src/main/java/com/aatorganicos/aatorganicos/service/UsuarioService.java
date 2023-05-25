package com.aatorganicos.aatorganicos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.model.Usuario;
import com.aatorganicos.aatorganicos.repository.IUsuarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> usuario() {
        return usuarioRepository.findAll();
    }
    
    public Usuario usuarioPorId(@NotNull @Positive Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Usuario criarUsuario(@Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizaUsuario(@NotNull @Positive Long id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(data -> {
                    data.setLogin(usuario.getLogin());
                    data.setSenha(usuario.getSenha());
                    return usuarioRepository.save(data);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteUsuario( @NotNull @Positive Long id) {
        usuarioRepository.findById(id)
                .map(data -> {
                    usuarioRepository.deleteById(id);
                    return true;
                }).orElseThrow(() -> new RecordNotFoundException(id)); 
    }
    
}
