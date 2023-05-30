package com.aatorganicos.aatorganicos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.dto.ProdutoDto;
import com.aatorganicos.aatorganicos.dto.mapper.ProdutoMapper;
import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.repository.IProdutoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ProdutoService {
    
    private final IProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(IProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public List<ProdutoDto> produto() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDto produtoPorId(@NotNull @Positive Long id) {
        return produtoRepository.findById(id).map(produtoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ProdutoDto criarProduto(@Valid ProdutoDto produto) {
        return produtoMapper.toDTO(produtoRepository.save(produtoMapper.toEntity(produto)));
    }

    public ProdutoDto atualizaProduto(@NotNull @Positive Long id,@Valid @NotNull ProdutoDto produto) {
        return produtoRepository.findById(id)
                .map(data -> {
                    data.setNome(produto.Nome());
                    data.setDescricao(produto.Descricao());
                    data.setCategoriaId(produto.CategoriaId());
                    return produtoMapper.toDTO(produtoRepository.save(data));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteProduto(@NotNull @Positive Long id) {

        produtoRepository.delete(produtoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
    
}
