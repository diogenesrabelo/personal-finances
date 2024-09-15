package com.dvmrabelo.personal_finances.core.domain.output;

import com.dvmrabelo.personal_finances.core.domain.Transacao;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioFinanceiroOutput(
        List<Transacao> transacoes,
        BigDecimal total
) {
}
