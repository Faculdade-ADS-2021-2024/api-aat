package com.aatorganicos.aatorganicos.dto.mapper;

import org.springframework.stereotype.Component;

import com.aatorganicos.aatorganicos.dto.ProdutoDto;
import com.aatorganicos.aatorganicos.model.Produto;

@Component
public class ProdutoMapper {
    
    public ProdutoDto toDTO(Produto produto) {
        if(produto == null) {
            return null;
        }

        return new ProdutoDto(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getCategoriaId());
    }

    public Produto toEntity(ProdutoDto produtoDto) {
        if(produtoDto == null) {
            return null;
        }
        
        Produto produto = new Produto();

        if (produtoDto.Id() != null) {
            produto.setId(produtoDto.Id());
        }

        produto.setNome(produtoDto.Nome());
        produto.setDescricao(produtoDto.Descricao());
        produto.setCategoriaId(produtoDto.CategoriaId());

        return produto;
    }
}
