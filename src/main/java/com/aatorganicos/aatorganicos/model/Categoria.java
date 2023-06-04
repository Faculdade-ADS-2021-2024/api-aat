package com.aatorganicos.aatorganicos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 120, nullable = false)
    private String nome;

    @NotBlank
    @NotNull
    @Column(length = 255, nullable = true)
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "categoria")
    private List<Produto> produtos = new ArrayList<>();

}
