package com.dvmrabelo.personal_finances.entrypoint.api.relatorio;

import com.dvmrabelo.personal_finances.core.domain.output.BalancoGeral;
import com.dvmrabelo.personal_finances.core.domain.output.RelatorioFinanceiroOutput;
import com.dvmrabelo.personal_finances.core.usecases.RelatorioFinanceiroUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioFinanceiroController {

    @Autowired
    private RelatorioFinanceiroUseCase relatorioFinanceiroService;

    @GetMapping("/transacoesEntrada/mensal")
    public ResponseEntity<RelatorioFinanceiroOutput> getEntradasMensais(
            @RequestParam int ano,
            @RequestParam int mes) {
        return ResponseEntity.ok(relatorioFinanceiroService.getRelatorioEntradasMensais(ano, mes));
    }

    @GetMapping("/transacoesEntrada/anual")
    public ResponseEntity<RelatorioFinanceiroOutput> getEntradasAnual(@RequestParam int ano) {
        return ResponseEntity.ok(relatorioFinanceiroService.getRelatorioEntradasAnual(ano));
    }

    @GetMapping("/transacoesSaida/mensal")
    public ResponseEntity<RelatorioFinanceiroOutput> getSaidasMensais(
            @RequestParam int ano,
            @RequestParam int mes) {
        return ResponseEntity.ok(relatorioFinanceiroService.getRelatorioSaidasMensais(ano, mes));
    }

    @GetMapping("/transacoesSaida/anual")
    public ResponseEntity<RelatorioFinanceiroOutput> getSaidasAnual(@RequestParam int ano) {
        return ResponseEntity.ok(relatorioFinanceiroService.getRelatorioSaidasAnual(ano));
    }

    @GetMapping("/balanco/mensal")
    public ResponseEntity<BalancoGeral> getBalancoMensal(
            @RequestParam int ano,
            @RequestParam int mes) {
        return ResponseEntity.ok(relatorioFinanceiroService.getBalancoMensal(ano, mes));
    }

    @GetMapping("/balanco/anual")
    public ResponseEntity<BalancoGeral> getBalancoAnual(@RequestParam int ano) {
        return ResponseEntity.ok(relatorioFinanceiroService.getBalancoAnual(ano));
    }
}