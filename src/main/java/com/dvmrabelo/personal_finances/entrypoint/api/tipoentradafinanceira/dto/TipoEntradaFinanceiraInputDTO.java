package com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto;

public record TipoEntradaFinanceiraInputDTO(
        String nome,
        String descricao,
        boolean ativo
) {}
