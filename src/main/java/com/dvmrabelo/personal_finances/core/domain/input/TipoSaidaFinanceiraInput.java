package com.dvmrabelo.personal_finances.core.domain.input;

public record TipoSaidaFinanceiraInput(
        String nome,
        String descricao,
        boolean ativo
) {
}
