package com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaidaFinanceiraOutputDTO(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Long tipoId,
        String descricao
) {
}
