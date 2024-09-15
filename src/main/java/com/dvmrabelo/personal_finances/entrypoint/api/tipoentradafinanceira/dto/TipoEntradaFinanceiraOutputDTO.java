package com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto;

public record TipoEntradaFinanceiraOutputDTO(
        Long id,
        String nome,
        String descricao,
        boolean ativo
) {}
