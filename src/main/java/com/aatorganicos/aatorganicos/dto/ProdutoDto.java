package com.aatorganicos.aatorganicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDto(
        Long Id,
        @NotNull @NotBlank String Nome,
        @NotNull @NotBlank String Descricao
        // Long categoriaId
        ) {}