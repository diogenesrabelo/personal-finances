package com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.dto;

public record EntradaFinanceiraTipoOutputDTO(
        Long id,
        String nome,
        String descricao,
        boolean ativo
) {}
