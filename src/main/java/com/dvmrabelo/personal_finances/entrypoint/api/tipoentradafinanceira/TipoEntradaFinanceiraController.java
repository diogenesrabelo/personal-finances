package com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira;

import com.dvmrabelo.personal_finances.core.usecases.EntradaFinanceiraTipoUseCase;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-entrada-financeira")
public class TipoEntradaFinanceiraController {

    @Autowired
    private EntradaFinanceiraTipoUseCase entradaFinanceiraTipoService;

    @GetMapping
    public ResponseEntity<List<TipoEntradaFinanceira>> findAll() {
        return ResponseEntity.ok(entradaFinanceiraTipoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEntradaFinanceira> findById(@PathVariable Long id) {
        return entradaFinanceiraTipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoEntradaFinanceira> save(@RequestBody TipoEntradaFinanceira tipoEntradaFinanceira) {
        TipoEntradaFinanceira savedTipo = entradaFinanceiraTipoService.createEntradaFinanceiraTipo(tipoEntradaFinanceira);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEntradaFinanceira> update(@PathVariable Long id, @RequestBody TipoEntradaFinanceira tipoEntradaFinanceira) {
        return entradaFinanceiraTipoService.updateEntradaFinanceiraTipo(id, tipoEntradaFinanceira)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        entradaFinanceiraTipoService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}