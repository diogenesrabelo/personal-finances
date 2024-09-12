package com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira;

import com.dvmrabelo.personal_finances.core.usecases.SaidaFinanceiraUseCase;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.entity.SaidaFinanceira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoesSaida-financeiras")
public class SaidaFinanceiraController {

    @Autowired
    private SaidaFinanceiraUseCase saidaFinanceiraService;

    @GetMapping
    public ResponseEntity<List<SaidaFinanceira>> findAll() {
        return ResponseEntity.ok(saidaFinanceiraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaFinanceira> findById(@PathVariable Long id) {
        return saidaFinanceiraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SaidaFinanceira> save(@RequestBody SaidaFinanceira saidaFinanceira) {
        SaidaFinanceira savedSaida = saidaFinanceiraService.createSaidaFinanceira(saidaFinanceira);
        return ResponseEntity.ok(savedSaida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaidaFinanceira> update(@PathVariable Long id, @RequestBody SaidaFinanceira saidaFinanceira) {
        return saidaFinanceiraService.updateSaidaFinanceira(id, saidaFinanceira)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (saidaFinanceiraService.findById(id).isPresent()) {
            saidaFinanceiraService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
