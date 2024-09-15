package com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.dto;

public record SaidaFinanceiraTipoOutputDTO(
        Long id,
        String nome,
        String descricao,
        boolean ativo
) {
}
