package com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira;

import com.dvmrabelo.personal_finances.core.usecases.SaidaFinanceiraTipoUseCase;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-saida")
public class TipoSaidaFinanceiraController {

    @Autowired
    private SaidaFinanceiraTipoUseCase saidaFinanceiraTipoService;

    @GetMapping
    public ResponseEntity<List<TipoSaidaFinanceira>> findAll() {
        return ResponseEntity.ok(saidaFinanceiraTipoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSaidaFinanceira> findById(@PathVariable Long id) {
        return saidaFinanceiraTipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoSaidaFinanceira> save(@RequestBody TipoSaidaFinanceira tipoSaidaFinanceira) {
        TipoSaidaFinanceira savedTipo = saidaFinanceiraTipoService.createSaidaFinanceiraTipo(tipoSaidaFinanceira);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoSaidaFinanceira> update(@PathVariable Long id, @RequestBody TipoSaidaFinanceira tipoSaidaFinanceira) {
        return saidaFinanceiraTipoService.updateSaidaFinanceiraTipo(id, tipoSaidaFinanceira)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        saidaFinanceiraTipoService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
