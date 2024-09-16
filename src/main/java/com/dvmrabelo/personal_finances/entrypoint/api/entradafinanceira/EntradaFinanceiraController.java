package com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira;

import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.facade.EntradaFinanceiraFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoesEntrada-financeiras")
public class EntradaFinanceiraController {

    @Autowired
    private EntradaFinanceiraFacade entradaFinanceiraFacade;

    @GetMapping
    public ResponseEntity<List<EntradaFinanceiraOutputDTO>> findAll() {
        return ResponseEntity.ok(entradaFinanceiraFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaFinanceiraOutputDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(entradaFinanceiraFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<EntradaFinanceiraOutputDTO> save(@RequestBody EntradaFinanceiraInputDTO entradaFinanceira) {
        EntradaFinanceiraOutputDTO savedEntrada = entradaFinanceiraFacade.createEntradaFinanceira(entradaFinanceira);
        return ResponseEntity.ok(savedEntrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaFinanceiraOutputDTO> update(@PathVariable Long id, @RequestBody EntradaFinanceiraInputDTO entradaFinanceira) {
        return ResponseEntity.ok(entradaFinanceiraFacade.updateEntradaFinanceira(id, entradaFinanceira));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entradaFinanceiraFacade.removeEntradaFinanceira(id);
        return ResponseEntity.notFound().build();
    }
}