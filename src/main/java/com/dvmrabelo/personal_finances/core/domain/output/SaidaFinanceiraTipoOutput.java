package com.dvmrabelo.personal_finances.core.domain.output;

public record SaidaFinanceiraTipoOutput(
        Long id,
        String nome,
        String descricao,
        boolean ativo
) {
}
