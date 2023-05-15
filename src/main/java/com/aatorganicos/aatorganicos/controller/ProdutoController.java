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

import com.aatorganicos.aatorganicos.model.Produto;
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
    public @ResponseBody List<Produto> produto() {
        return produtoService.produto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> produtoPorId(@PathVariable @NotNull @Positive Long id) {
        return produtoService.produtoPorId(id)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produto criarProduto(@RequestBody @Valid Produto produto) {
        return produtoService.criarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizaProduto(@PathVariable @NotNull @Positive Long id, @RequestBody Produto produto) {
        return produtoService.atualizaProduto(id, produto)
                .map(data -> {
                    return ResponseEntity.ok().body(data);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable @NotNull @Positive Long id) {
        if(produtoService.deleteProduto(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
