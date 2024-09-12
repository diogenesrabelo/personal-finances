package com.dvmrabelo.personal_finances.core.domain;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioFinanceiro(
        List<Transacao> transacoes,
        BigDecimal total
) {
}
