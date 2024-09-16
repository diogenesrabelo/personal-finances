package com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira;

import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.facade.SaidaFinanceiraFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoesSaida-financeiras")
public class SaidaFinanceiraController {

    @Autowired
    private SaidaFinanceiraFacade saidaFinanceiraFacade;

    @GetMapping
    public ResponseEntity<List<SaidaFinanceiraOutputDTO>> findAll() {
        return ResponseEntity.ok(saidaFinanceiraFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaFinanceiraOutputDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(saidaFinanceiraFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<SaidaFinanceiraOutputDTO> save(@RequestBody SaidaFinanceiraInputDTO saidaFinanceira) {
        SaidaFinanceiraOutputDTO savedSaida = saidaFinanceiraFacade.createSaidaFinanceira(saidaFinanceira);
        return ResponseEntity.ok(savedSaida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaidaFinanceiraOutputDTO> update(@PathVariable Long id, @RequestBody SaidaFinanceiraInputDTO saidaFinanceira) {
        return ResponseEntity.ok(saidaFinanceiraFacade.updateSaidaFinanceira(id, saidaFinanceira));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saidaFinanceiraFacade.removeSaidaFinanceira(id);

        return ResponseEntity.notFound().build();
    }
}
