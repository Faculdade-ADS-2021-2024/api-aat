package com.aatorganicos.aatorganicos.dto.mapper;

import org.springframework.stereotype.Component;

import com.aatorganicos.aatorganicos.dto.CategoriaDto;
import com.aatorganicos.aatorganicos.model.Categoria;

@Component
public class CategoriaMapper {
    
    public CategoriaDto toDTO(Categoria categoria) {
        if(categoria == null) {
            return null;            
        }

        return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getDescricao(), categoria.getProdutos());
    }

    public Categoria toEntity(CategoriaDto categoriaDto) {
        if(categoriaDto == null) {
            return null;
        }

        Categoria categoria = new Categoria();

        if(categoriaDto.Id() != null) {
            categoria.setId(categoriaDto.Id());
        }

        categoria.setNome(categoriaDto.Nome());
        categoria.setDescricao(categoriaDto.Descricao());
        
        return categoria;
    }    
}
