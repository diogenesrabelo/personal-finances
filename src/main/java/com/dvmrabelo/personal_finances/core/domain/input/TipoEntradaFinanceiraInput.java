package com.dvmrabelo.personal_finances.core.domain.input;

public record TipoEntradaFinanceiraInput(
        String nome,
        String descricao,
        boolean ativo
) {}
