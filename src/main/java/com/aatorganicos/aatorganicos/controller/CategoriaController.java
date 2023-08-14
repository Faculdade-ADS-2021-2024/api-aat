package com.aatorganicos.aatorganicos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.aatorganicos.aatorganicos.dto.CategoriaDto;
import com.aatorganicos.aatorganicos.service.CategoriaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaDto> categoria() {
        return categoriaService.categoria();
    }

    @GetMapping("/{id}")
    public CategoriaDto categoriaPorId(@PathVariable @NotNull @Positive Long id) {
        return categoriaService.categoriaPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CategoriaDto criarCategoria(@RequestBody @Valid CategoriaDto categoria) {
        return categoriaService.criarCategoria(categoria);    
    }

    @PutMapping("/{id}")
    public CategoriaDto atualizaCategoria(@PathVariable @NotNull @Positive Long id, @RequestBody CategoriaDto categoria) {
        return categoriaService.atualizaCategoria(id, categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCategoria(@PathVariable @NotNull @Positive Long id) {

        categoriaService.deleteCategoria(id);
    }
    
}
