package com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.dto;

public record SaidaFinanceiraTipoInputDTO(
        String nome,
        String descricao,
        boolean ativo
) {
}
