package com.dvmrabelo.personal_finances.core.domain.input;

public record SaidaFinanceiraTipoInput(
        String nome,
        String descricao,
        boolean ativo
) {
}
