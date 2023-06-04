package com.aatorganicos.aatorganicos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aatorganicos.aatorganicos.dto.UsuarioDto;
import com.aatorganicos.aatorganicos.service.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDto> usuario() {
        return usuarioService.usuario();
    }

    @GetMapping("/{id}")
    public UsuarioDto usuarioPorId(@PathVariable @NotNull @Positive Long id) {
        return usuarioService.usuarioPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioDto criarUsuario(@RequestBody @Valid UsuarioDto usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioDto atualizaUsuario(@PathVariable @NotNull @Positive Long id, @RequestBody UsuarioDto usuario) {
        return usuarioService.atualizaUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable @NotNull @Positive Long id) {

        usuarioService.deleteUsuario(id);
    }

}
