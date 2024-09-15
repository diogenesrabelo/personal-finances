package com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto;

public record TipoSaidaFinanceiraInputDTO(
        String nome,
        String descricao,
        boolean ativo
) {
}
