package com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira;

import com.dvmrabelo.personal_finances.core.usecases.SaidaFinanceiraTipoUseCase;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.SaidaFinanceiraTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-saida")
public class SaidaFinanceiraTipoController {

    @Autowired
    private SaidaFinanceiraTipoUseCase saidaFinanceiraTipoService;

    @GetMapping
    public ResponseEntity<List<SaidaFinanceiraTipo>> findAll() {
        return ResponseEntity.ok(saidaFinanceiraTipoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaFinanceiraTipo> findById(@PathVariable Long id) {
        return saidaFinanceiraTipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SaidaFinanceiraTipo> save(@RequestBody SaidaFinanceiraTipo saidaFinanceiraTipo) {
        SaidaFinanceiraTipo savedTipo = saidaFinanceiraTipoService.createSaidaFinanceiraTipo(saidaFinanceiraTipo);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaidaFinanceiraTipo> update(@PathVariable Long id, @RequestBody SaidaFinanceiraTipo saidaFinanceiraTipo) {
        return saidaFinanceiraTipoService.updateSaidaFinanceiraTipo(id, saidaFinanceiraTipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        saidaFinanceiraTipoService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
