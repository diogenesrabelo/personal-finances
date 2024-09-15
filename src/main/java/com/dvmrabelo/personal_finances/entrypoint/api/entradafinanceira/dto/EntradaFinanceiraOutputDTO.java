package com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EntradaFinanceiraOutputDTO(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Long tipoId,
        String descricao
) {
}
