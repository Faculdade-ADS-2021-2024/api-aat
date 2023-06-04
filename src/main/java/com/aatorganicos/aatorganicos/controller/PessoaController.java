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

import com.aatorganicos.aatorganicos.dto.PessoaDto;
import com.aatorganicos.aatorganicos.service.PessoaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<PessoaDto> pessoa() {
        return pessoaService.pessoa();
    }

    @GetMapping("/{id}")
    public PessoaDto pessoaPorId(@PathVariable @NotNull @Positive Long id) {
        return pessoaService.pessoaPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PessoaDto criarPessoa(@RequestBody @Valid PessoaDto pessoa) {
        return pessoaService.criarPessoa(pessoa);
    }

    @PutMapping("/{id}")
    public PessoaDto atualizaPessoa(@PathVariable @NotNull @Positive Long id, @RequestBody PessoaDto pessoa) {
        return pessoaService.atualizaPessoa(id, pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePessoa(@PathVariable @NotNull @Positive Long id) {

        pessoaService.deletePessoa(id);
    }

}
