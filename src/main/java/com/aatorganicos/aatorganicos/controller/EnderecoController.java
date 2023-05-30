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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aatorganicos.aatorganicos.dto.EnderecoDto;
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
    public @ResponseBody List<EnderecoDto> endereco() {
        return enderecoService.endereco();
    }

    @GetMapping("/{id}")
    public EnderecoDto enderecoPorId(@PathVariable @NotNull @Positive Long id) {
        return enderecoService.enderecoPorId(id);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EnderecoDto criarEndereco(@RequestBody @Valid EnderecoDto endereco) {
        return enderecoService.criarEndereco(endereco);
    }

    @PutMapping("/{id}")
    public EnderecoDto atualizaEndereco(@PathVariable @NotNull @Positive Long id, @RequestBody EnderecoDto endereco) {
        return enderecoService.atualizaEndereco(id, endereco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEndereco(@PathVariable @NotNull @Positive Long id) {

        enderecoService.deleteEndereco(id);
    }

}
