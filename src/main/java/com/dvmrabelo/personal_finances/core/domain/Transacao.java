package com.dvmrabelo.personal_finances.core.domain;

import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.entity.SaidaFinanceira;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transacao(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        String mes
) {
    public static Transacao convertToTransacaoEntrada(EntradaFinanceira entrada) {
        return new Transacao(
                entrada.getId(),
                entrada.getTipo().nome(),
                entrada.getDescricao(),
                entrada.getValor(),
                entrada.getData(),
                entrada.getData().getMonth().name()
        );
    }

    public static Transacao convertToTransacaoSaida(SaidaFinanceira saida) {
        return new Transacao(
                saida.getId(),
                saida.getTipo().nome(),
                saida.getDescricao(),
                saida.getValor(),
                saida.getData(),
                saida.getData().getMonth().name()
        );
    }
}
