package com.dvmrabelo.personal_finances.core.domain.output;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaidaFinanceiraOutput(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Long tipoId,
        String descricao
) {
}
