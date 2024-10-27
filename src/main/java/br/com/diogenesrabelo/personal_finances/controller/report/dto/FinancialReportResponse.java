package br.com.diogenesrabelo.personal_finances.controller.report.dto;

import java.math.BigDecimal;
import java.util.List;

public record FinancialReportResponse(
        List<Transaction> transactions,
        BigDecimal total
) {
}
