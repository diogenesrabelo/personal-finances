package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.BalancoGeral;
import com.dvmrabelo.personal_finances.core.domain.RelatorioFinanceiro;
import com.dvmrabelo.personal_finances.core.domain.Transacao;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.repository.EntradaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.repository.SaidaFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioFinanceiroUseCase {

    @Autowired
    private EntradaFinanceiraRepository entradaFinanceiraRepository;

    @Autowired
    private SaidaFinanceiraRepository saidaFinanceiraRepository;

    public RelatorioFinanceiro getRelatorioEntradasMensais(int ano, int mes) {
        List<Transacao> transacoes = entradaFinanceiraRepository.findByAnoAndMes(ano, mes)
                .stream()
                .map(Transacao::convertToTransacaoEntrada)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiro(transacoes, total);
    }

    public RelatorioFinanceiro getRelatorioEntradasAnual(int ano) {
        List<Transacao> transacoes = entradaFinanceiraRepository.findByAno(ano)
                .stream()
                .map(Transacao::convertToTransacaoEntrada)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiro(transacoes, total);
    }

    public RelatorioFinanceiro getRelatorioSaidasMensais(int ano, int mes) {
        List<Transacao> transacoes = saidaFinanceiraRepository.findByAnoAndMes(ano, mes)
                .stream()
                .map(Transacao::convertToTransacaoSaida)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiro(transacoes, total);
    }

    public RelatorioFinanceiro getRelatorioSaidasAnual(int ano) {
        List<Transacao> transacoes = saidaFinanceiraRepository.findByAno(ano)
                .stream()
                .map(Transacao::convertToTransacaoSaida)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiro(transacoes, total);
    }

    public BalancoGeral getBalancoMensal(int ano, int mes) {
        RelatorioFinanceiro entradas = getRelatorioEntradasMensais(ano, mes);
        RelatorioFinanceiro saidas = getRelatorioSaidasMensais(ano, mes);

        BigDecimal diferenca = entradas.total().subtract(saidas.total());

        return new BalancoGeral(
                entradas.transacoes(),
                saidas.transacoes(),
                entradas.total(),
                saidas.total(),
                diferenca
        );
    }

    public BalancoGeral getBalancoAnual(int ano) {
        RelatorioFinanceiro entradas = getRelatorioEntradasAnual(ano);
        RelatorioFinanceiro saidas = getRelatorioSaidasAnual(ano);

        BigDecimal diferenca = entradas.total().subtract(saidas.total());

        return new BalancoGeral(
                entradas.transacoes(),
                saidas.transacoes(),
                entradas.total(),
                saidas.total(),
                diferenca
        );
    }
}