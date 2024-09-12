package com.dvmrabelo.personal_finances.core.domain;

import java.math.BigDecimal;
import java.util.List;

public record BalancoGeral(
        List<Transacao> transacoesEntrada,
        List<Transacao> transacoesSaida,
        BigDecimal totalEntradas,
        BigDecimal totalSaidas,
        BigDecimal diferenca
) {}
