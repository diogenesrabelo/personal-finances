package com.dvmrabelo.personal_finances.core.domain.input;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaidaFinanceiraInput(
        LocalDate data,
        BigDecimal valor,
        Long tipoId,
        String descricao
) {
}
