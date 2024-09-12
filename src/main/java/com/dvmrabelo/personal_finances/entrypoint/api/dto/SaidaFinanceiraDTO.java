package com.dvmrabelo.personal_finances.entrypoint.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaidaFinanceiraDTO(
        LocalDate data,
        BigDecimal valor,
        Long tipoId,
        String descricao
) {
}
