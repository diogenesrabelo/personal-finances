package br.com.diogenesrabelo.personal_finances.controller.report.dto;

import java.math.BigDecimal;
import java.util.List;

public record GeneralBalanceResponse(
        List<Transaction> transactionsCashin,
        List<Transaction> transactionCashout,
        BigDecimal totalCashin,
        BigDecimal totalCashout,
        BigDecimal difference
) {
    @Override
    public String toString() {
        return "GeneralBalanceResponse{" +
            "transactionsCashin=" + transactionsCashin.toString() +
            ", transactionCashout=" + transactionCashout.toString() +
            ", totalCashin=" + totalCashin +
            ", totalCashout=" + totalCashout +
            ", difference=" + difference +
            '}';
    }
}
