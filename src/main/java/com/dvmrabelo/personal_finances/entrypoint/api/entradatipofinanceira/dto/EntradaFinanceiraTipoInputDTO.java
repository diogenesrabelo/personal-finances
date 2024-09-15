package com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.dto;

public record EntradaFinanceiraTipoInputDTO(
        String nome,
        String descricao,
        boolean ativo
) {}
