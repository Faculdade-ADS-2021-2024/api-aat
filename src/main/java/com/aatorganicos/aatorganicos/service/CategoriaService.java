package com.aatorganicos.aatorganicos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.model.Categoria;
import com.aatorganicos.aatorganicos.repository.ICategoriaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CategoriaService {

    private final ICategoriaRepository categoriaRepository;

    public CategoriaService(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> categoria() {
        return categoriaRepository.findAll();
    }

    public Categoria categoriaPorId(@NotNull @Positive Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Categoria criarCategoria(@Valid Categoria categoria) {
        return categoriaRepository.save(categoria);    
    }

    public Categoria atualizaCategoria(@NotNull @Positive Long id, Categoria categoria) {
        return categoriaRepository.findById(id)
                .map(data -> {
                    data.setNome(categoria.getNome());
                    data.setDescricao(categoria.getDescricao());
                    return categoriaRepository.save(data);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteCategoria(@NotNull @Positive Long id) {
        categoriaRepository.findById(id)
                .map(data -> {
                    categoriaRepository.deleteById(id);
                    return true;
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }


}
