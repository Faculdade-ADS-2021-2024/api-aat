package com.aatorganicos.aatorganicos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aatorganicos.aatorganicos.model.Usuario;
import com.aatorganicos.aatorganicos.repository.IUsuarioRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final IUsuarioRepository usuarioRepository;

    @GetMapping
    public @ResponseBody List<Usuario> usuario() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> usuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
            .map(data -> ResponseEntity.ok().body(data))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody Usuario usuario) {

        return usuarioRepository.save(usuario);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizaUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(data -> {
                    data.setLogin(usuario.getLogin());
                    data.setSenha(usuario.getSenha());
                    Usuario updated = usuarioRepository.save(data);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
