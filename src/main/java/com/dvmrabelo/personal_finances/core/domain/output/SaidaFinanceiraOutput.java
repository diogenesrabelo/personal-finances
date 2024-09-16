package com.dvmrabelo.personal_finances.core.domain.output;

import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaidaFinanceiraOutput(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Long tipoId,
        UserEntity createdBy,
        String descricao
) {
}
