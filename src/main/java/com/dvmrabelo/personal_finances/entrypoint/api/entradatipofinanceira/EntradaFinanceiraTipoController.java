package com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira;

import com.dvmrabelo.personal_finances.core.usecases.EntradaFinanceiraTipoUseCase;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.EntradaFinanceiraTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-entrada-financeira")
public class EntradaFinanceiraTipoController {

    @Autowired
    private EntradaFinanceiraTipoUseCase entradaFinanceiraTipoService;

    @GetMapping
    public ResponseEntity<List<EntradaFinanceiraTipo>> findAll() {
        return ResponseEntity.ok(entradaFinanceiraTipoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaFinanceiraTipo> findById(@PathVariable Long id) {
        return entradaFinanceiraTipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntradaFinanceiraTipo> save(@RequestBody EntradaFinanceiraTipo entradaFinanceiraTipo) {
        EntradaFinanceiraTipo savedTipo = entradaFinanceiraTipoService.createEntradaFinanceiraTipo(entradaFinanceiraTipo);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaFinanceiraTipo> update(@PathVariable Long id, @RequestBody EntradaFinanceiraTipo entradaFinanceiraTipo) {
        return entradaFinanceiraTipoService.updateEntradaFinanceiraTipo(id, entradaFinanceiraTipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        entradaFinanceiraTipoService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}