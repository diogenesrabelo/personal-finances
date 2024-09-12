package com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira;

import com.dvmrabelo.personal_finances.core.usecases.EntradaFinanceiraUseCase;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoesEntrada-financeiras")
public class EntradaFinanceiraController {

    @Autowired
    private EntradaFinanceiraUseCase entradaFinanceiraService;

    @GetMapping
    public ResponseEntity<List<EntradaFinanceira>> findAll() {
        return ResponseEntity.ok(entradaFinanceiraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaFinanceira> findById(@PathVariable Long id) {
        return entradaFinanceiraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntradaFinanceira> save(@RequestBody EntradaFinanceira entradaFinanceira) {
        EntradaFinanceira savedEntrada = entradaFinanceiraService.createEntradaFinanceira(entradaFinanceira);
        return ResponseEntity.ok(savedEntrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaFinanceira> update(@PathVariable Long id, @RequestBody EntradaFinanceira entradaFinanceira) {
        return entradaFinanceiraService.updateEntradaFinanceira(id, entradaFinanceira)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (entradaFinanceiraService.findById(id).isPresent()) {
            entradaFinanceiraService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}