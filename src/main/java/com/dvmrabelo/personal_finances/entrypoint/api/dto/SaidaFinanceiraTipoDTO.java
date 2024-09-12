package com.dvmrabelo.personal_finances.entrypoint.api.dto;

public record SaidaFinanceiraTipoDTO(
        Long id,
        String nome,
        String descricao,
        boolean ativo
) {
}
