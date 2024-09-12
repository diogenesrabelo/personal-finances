package com.dvmrabelo.personal_finances.entrypoint.api.dto;

public record EntradaFinanceiraTipoDTO(
        String nome,
        String descricao,
        boolean ativo
) {}
