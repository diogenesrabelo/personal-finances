package com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto;

public record TipoSaidaFinanceiraOutputDTO(
        Long id,
        String nome,
        String descricao,
        boolean ativo
) {
}
