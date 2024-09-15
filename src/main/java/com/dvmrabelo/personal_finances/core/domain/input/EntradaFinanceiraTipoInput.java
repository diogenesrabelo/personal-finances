package com.dvmrabelo.personal_finances.core.domain.input;

public record EntradaFinanceiraTipoInput(
        String nome,
        String descricao,
        boolean ativo
) {}
