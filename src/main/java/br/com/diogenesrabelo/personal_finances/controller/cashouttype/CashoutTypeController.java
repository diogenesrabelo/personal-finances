package br.com.diogenesrabelo.personal_finances.controller.cashouttype;

import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeResponse;
import br.com.diogenesrabelo.personal_finances.service.cashouttype.CashoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cashout-type")
public class CashoutTypeController {

    @Autowired
    private CashoutTypeService cashoutTypeService;

    @GetMapping
    public ResponseEntity<List<CashoutTypeResponse>> findAll(@AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashoutTypeService.findAll(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashoutTypeResponse> findById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashoutTypeService.findById(id, user));
    }

    @PostMapping
    public ResponseEntity<CashoutTypeResponse> save(@RequestBody CashoutTypeRequest cashoutTypeRequest, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        CashoutTypeResponse savedTipo = cashoutTypeService.createCashoutType(cashoutTypeRequest, user);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashoutTypeResponse> update(@PathVariable Long id,
                                                      @RequestBody CashoutTypeRequest tipoSaidaFinanceira,
                                                      @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashoutTypeService.updateCashoutType(id, tipoSaidaFinanceira, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id,
                                           @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        cashoutTypeService.deactivate(id, user);
        return ResponseEntity.noContent().build();
    }
}
