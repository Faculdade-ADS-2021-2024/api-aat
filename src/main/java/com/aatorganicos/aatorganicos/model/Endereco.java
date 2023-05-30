package com.aatorganicos.aatorganicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank
    @NotNull
    @Column(length = 10, nullable = false)
    private String Cep;

    @Column(length = 120, nullable = true)
    private String Logradouro;

    @Column(length = 120, nullable = true)
    private String Cidade;

    @Column(length = 120, nullable = true)
    private String Bairro;

    @Column(length = 120, nullable = true)
    private String Complemento;

    private Long PessoaId;
}
