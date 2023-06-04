package com.aatorganicos.aatorganicos.dto;

import java.util.List;

import com.aatorganicos.aatorganicos.model.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaDto(
        Long Id,
        @NotNull @NotBlank String Nome,
        @NotNull @NotBlank String Descricao,
        List<Produto> produtos
        ) {}


