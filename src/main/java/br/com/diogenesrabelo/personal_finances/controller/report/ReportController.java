package br.com.diogenesrabelo.personal_finances.controller.report;

import br.com.diogenesrabelo.personal_finances.controller.report.dto.FinancialReportResponse;
import br.com.diogenesrabelo.personal_finances.controller.report.dto.GeneralBalanceResponse;
import br.com.diogenesrabelo.personal_finances.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/cashin/monthly")
    public ResponseEntity<FinancialReportResponse> getCashinMonthly(
        @RequestParam int year,
        @RequestParam int month,
        @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(reportService.getReportCashinMonthly(year, month, user));
    }

    @GetMapping("/cashin/annual")
    public ResponseEntity<FinancialReportResponse> getCashinAnnual(@RequestParam int year,
                                                                   @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(reportService.getReportCashinAnnual(year, user));
    }

    @GetMapping("/cashout/monthly")
    public ResponseEntity<FinancialReportResponse> getCashoutMonthly(
            @RequestParam int year,
            @RequestParam int month,
            @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(reportService.getReportCashoutMonthly(year, month, user));
    }

    @GetMapping("/cashout/annual")
    public ResponseEntity<FinancialReportResponse> getCashoutAnnual(@RequestParam int year,
                                                                    @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(reportService.getReportCashoutAnnual(year, user));
    }

    @GetMapping("/balance/monthly")
    public ResponseEntity<GeneralBalanceResponse> getBalanceMonthly(
            @RequestParam int year,
            @RequestParam int month,
            @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(reportService.getBalanceMonthly(year, month, user));
    }

    @GetMapping("/balance/annual")
    public ResponseEntity<GeneralBalanceResponse> getBalanceAnnual(@RequestParam int year,
                                                                   @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(reportService.getBalanceAnnual(year, user));
    }
}