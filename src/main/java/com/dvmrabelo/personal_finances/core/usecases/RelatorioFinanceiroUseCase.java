package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.output.BalancoGeral;
import com.dvmrabelo.personal_finances.core.domain.output.RelatorioFinanceiroOutput;
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

    public RelatorioFinanceiroOutput getRelatorioEntradasMensais(int ano, int mes) {
        List<Transacao> transacoes = entradaFinanceiraRepository.findByAnoAndMes(ano, mes)
                .stream()
                .map(Transacao::convertToTransacaoEntrada)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiroOutput(transacoes, total);
    }

    public RelatorioFinanceiroOutput getRelatorioEntradasAnual(int ano) {
        List<Transacao> transacoes = entradaFinanceiraRepository.findByAno(ano)
                .stream()
                .map(Transacao::convertToTransacaoEntrada)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiroOutput(transacoes, total);
    }

    public RelatorioFinanceiroOutput getRelatorioSaidasMensais(int ano, int mes) {
        List<Transacao> transacoes = saidaFinanceiraRepository.findByAnoAndMes(ano, mes)
                .stream()
                .map(Transacao::convertToTransacaoSaida)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiroOutput(transacoes, total);
    }

    public RelatorioFinanceiroOutput getRelatorioSaidasAnual(int ano) {
        List<Transacao> transacoes = saidaFinanceiraRepository.findByAno(ano)
                .stream()
                .map(Transacao::convertToTransacaoSaida)
                .collect(Collectors.toList());

        BigDecimal total = transacoes.stream()
                .map(Transacao::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiroOutput(transacoes, total);
    }

    public BalancoGeral getBalancoMensal(int ano, int mes) {
        RelatorioFinanceiroOutput entradas = getRelatorioEntradasMensais(ano, mes);
        RelatorioFinanceiroOutput saidas = getRelatorioSaidasMensais(ano, mes);

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
        RelatorioFinanceiroOutput entradas = getRelatorioEntradasAnual(ano);
        RelatorioFinanceiroOutput saidas = getRelatorioSaidasAnual(ano);

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