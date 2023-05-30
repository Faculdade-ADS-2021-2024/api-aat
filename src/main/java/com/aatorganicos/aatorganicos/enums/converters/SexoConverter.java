package com.aatorganicos.aatorganicos.enums.converters;

import java.util.stream.Stream;

import com.aatorganicos.aatorganicos.enums.Sexo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, String>{

    @Override
    public String convertToDatabaseColumn(Sexo sexo) {
        if(sexo == null){
            return null;
        }
        return sexo.getValue();
    }

    @Override
    public Sexo convertToEntityAttribute(String value) {
        if(value == null){
            return null;
        }

        return Stream.of(Sexo.values())
        .filter(sexo -> sexo.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
    
}
