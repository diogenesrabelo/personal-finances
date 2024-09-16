package com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira;

import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.usecases.EntradaFinanceiraTipoUseCase;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.facade.TipoEntradaFinanceiraFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-entrada-financeira")
public class TipoEntradaFinanceiraController {

    @Autowired
    private TipoEntradaFinanceiraFacade entradaFinanceiraTipoService;

    @GetMapping
    public ResponseEntity<List<TipoEntradaFinanceiraOutputDTO>> findAll() {
        return ResponseEntity.ok(entradaFinanceiraTipoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEntradaFinanceiraOutputDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(entradaFinanceiraTipoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoEntradaFinanceiraOutputDTO> save(@RequestBody TipoEntradaFinanceiraInputDTO tipoEntradaFinanceira) {
        TipoEntradaFinanceiraOutputDTO savedTipo = entradaFinanceiraTipoService.createTipoEntradaFinanceira(tipoEntradaFinanceira);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEntradaFinanceiraOutputDTO> update(@PathVariable Long id, @RequestBody TipoEntradaFinanceiraInputDTO tipoEntradaFinanceira) {
        return ResponseEntity.ok(entradaFinanceiraTipoService.updateTipoEntradaFinanceira(id, tipoEntradaFinanceira));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        entradaFinanceiraTipoService.deactivateTipoEntradaFinanceira(id);
        return ResponseEntity.noContent().build();
    }
}