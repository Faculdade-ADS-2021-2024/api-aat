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

import com.aatorganicos.aatorganicos.dto.ProdutoDto;
import com.aatorganicos.aatorganicos.service.ProdutoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoDto> produto() {
        return produtoService.produto();
    }

    @GetMapping("/{id}")
    public ProdutoDto produtoPorId(@PathVariable @NotNull @Positive Long id) {
        return produtoService.produtoPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProdutoDto criarProduto(@RequestBody @Valid ProdutoDto produto) {
        return produtoService.criarProduto(produto);
    }

    @PutMapping("/{id}")
    public ProdutoDto atualizaProduto(@PathVariable @NotNull @Positive Long id, @RequestBody ProdutoDto produto) {
        return produtoService.atualizaProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable @NotNull @Positive Long id) {

        produtoService.deleteProduto(id);
    }
    
}
