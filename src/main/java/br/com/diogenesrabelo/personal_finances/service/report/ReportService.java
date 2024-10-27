package br.com.diogenesrabelo.personal_finances.service.report;

import br.com.diogenesrabelo.personal_finances.controller.report.dto.FinancialReportResponse;
import br.com.diogenesrabelo.personal_finances.controller.report.dto.GeneralBalanceResponse;

public interface ReportService {

    FinancialReportResponse getReportCashinMonthly(int ano, int mes, String user);
    FinancialReportResponse getReportCashinAnnual(int ano, String user);
    FinancialReportResponse getReportCashoutMonthly(int year, int month, String user);
    FinancialReportResponse getReportCashoutAnnual(int year, String user);
    GeneralBalanceResponse getBalanceMonthly(int year, int month, String user);
    GeneralBalanceResponse getBalanceAnnual(int year, String user);
}
