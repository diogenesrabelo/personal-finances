package br.com.diogenesrabelo.personal_finances.service.report.impl;

import br.com.diogenesrabelo.personal_finances.controller.report.dto.FinancialReportResponse;
import br.com.diogenesrabelo.personal_finances.controller.report.dto.GeneralBalanceResponse;
import br.com.diogenesrabelo.personal_finances.controller.report.dto.Transaction;
import br.com.diogenesrabelo.personal_finances.repository.cashin.CashinRepository;
import br.com.diogenesrabelo.personal_finances.repository.cashout.CashoutRepository;
import br.com.diogenesrabelo.personal_finances.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CashinRepository cashinRepository;

    @Autowired
    private CashoutRepository cashoutRepository;

    public FinancialReportResponse getReportCashinMonthly(int year, int month, String user) {
        List<Transaction> transactions = cashinRepository.findByYearAndMonth(year, month, user)
                .stream()
                .map(Transaction::convertToTransactionCashin)
                .collect(Collectors.toList());

        BigDecimal total = transactions.stream()
                .map(Transaction::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new FinancialReportResponse(transactions, total);
    }

    public FinancialReportResponse getReportCashinAnnual(int year, String user) {
        List<Transaction> transactions = cashinRepository.findByYear(year, user)
                .stream()
                .map(Transaction::convertToTransactionCashin)
                .collect(Collectors.toList());

        BigDecimal total = transactions.stream()
                .map(Transaction::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new FinancialReportResponse(transactions, total);
    }

    public FinancialReportResponse getReportCashoutMonthly(int year, int month, String user) {
        List<Transaction> transactions = cashoutRepository.findByYearAndMonth(year, month, user)
                .stream()
                .map(Transaction::convertToTransactionCashout)
                .collect(Collectors.toList());

        BigDecimal total = transactions.stream()
                .map(Transaction::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new FinancialReportResponse(transactions, total);
    }

    public FinancialReportResponse getReportCashoutAnnual(int year, String user) {
        List<Transaction> transactions = cashoutRepository.findByYear(year, user)
                .stream()
                .map(Transaction::convertToTransactionCashout)
                .collect(Collectors.toList());

        BigDecimal total = transactions.stream()
                .map(Transaction::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new FinancialReportResponse(transactions, total);
    }

    public GeneralBalanceResponse getBalanceMonthly(int year, int month, String user) {
        FinancialReportResponse entradas = getReportCashinMonthly(year, month, user);
        FinancialReportResponse saidas = getReportCashoutMonthly(year, month, user);

        BigDecimal difference = entradas.total().subtract(saidas.total());

        return new GeneralBalanceResponse(
            entradas.transactions(),
            saidas.transactions(),
            entradas.total(),
            saidas.total(),
            difference
        );
    }

    public GeneralBalanceResponse getBalanceAnnual(int year, String user) {
        FinancialReportResponse cashinReport = getReportCashinAnnual(year, user);
        FinancialReportResponse cashoutReport = getReportCashoutAnnual(year, user);

        BigDecimal diferenca = cashinReport.total().subtract(cashoutReport.total());

        return new GeneralBalanceResponse(
                cashinReport.transactions(),
                cashoutReport.transactions(),
                cashinReport.total(),
                cashoutReport.total(),
                diferenca
        );
    }
}