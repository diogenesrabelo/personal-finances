package com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira;

import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.facade.TipoSaidaFinanceiraFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-saida")
public class TipoSaidaFinanceiraController {

    @Autowired
    private TipoSaidaFinanceiraFacade tipoSaidaFinanceiraFacade;

    @GetMapping
    public ResponseEntity<List<TipoSaidaFinanceiraOutputDTO>> findAll() {
        return ResponseEntity.ok(tipoSaidaFinanceiraFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSaidaFinanceiraOutputDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoSaidaFinanceiraFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoSaidaFinanceiraOutputDTO> save(@RequestBody TipoSaidaFinanceiraInputDTO tipoSaidaFinanceira) {
        TipoSaidaFinanceiraOutputDTO savedTipo = tipoSaidaFinanceiraFacade.createTipoSaidaFinanceira(tipoSaidaFinanceira);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoSaidaFinanceiraOutputDTO> update(@PathVariable Long id, @RequestBody TipoSaidaFinanceiraInputDTO tipoSaidaFinanceira) {
        return ResponseEntity.ok(tipoSaidaFinanceiraFacade.updateTipoSaidaFinanceira(id, tipoSaidaFinanceira));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        tipoSaidaFinanceiraFacade.deactivateTipoSaidaFinanceira(id);
        return ResponseEntity.noContent().build();
    }
}
