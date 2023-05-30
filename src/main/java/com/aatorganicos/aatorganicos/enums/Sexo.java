package com.aatorganicos.aatorganicos.enums;

public enum Sexo {
    MASCULINO("M"),
    FEMININO("F"),
    OUTROS("O");

    private String value;

    private Sexo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
