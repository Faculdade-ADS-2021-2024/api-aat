package com.aatorganicos.aatorganicos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aatorganicos.aatorganicos.model.Endereco;
import com.aatorganicos.aatorganicos.service.EnderecoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public @ResponseBody List<Endereco> endereco() {
        return enderecoService.endereco();
    }

    @GetMapping("/{id}")
    public Endereco enderecoPorId(@PathVariable @NotNull @Positive Long id) {
        return enderecoService.enderecoPorId(id);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Endereco criarEndereco(@RequestBody @Valid Endereco endereco) {
        return enderecoService.criarEndereco(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizaEndereco(@PathVariable @NotNull @Positive Long id, @RequestBody Endereco endereco) {
        return enderecoService.atualizaEndereco(id, endereco)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable @NotNull @Positive Long id) {
        if(enderecoService.deleteEndereco(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }

}
