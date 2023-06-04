package com.aatorganicos.aatorganicos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.dto.CategoriaDto;
import com.aatorganicos.aatorganicos.dto.mapper.CategoriaMapper;
import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.repository.ICategoriaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CategoriaService {

    private final ICategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(ICategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaDto> categoria() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDto categoriaPorId(@NotNull @Positive Long id) {
        return categoriaRepository.findById(id).map(categoriaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CategoriaDto criarCategoria(@Valid CategoriaDto categoria) {
        return categoriaMapper.toDTO(categoriaRepository.save(categoriaMapper.toEntity(categoria)));
    }    

    public CategoriaDto atualizaCategoria(@NotNull @Positive Long id,@Valid @NotNull CategoriaDto categoria) {
        return categoriaRepository.findById(id)
                .map(data -> {
                    data.setNome(categoria.Nome());
                    data.setDescricao(categoria.Descricao());
                    return categoriaMapper.toDTO(categoriaRepository.save(data));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteCategoria(@NotNull @Positive Long id) {
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }


}
