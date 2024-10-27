package br.com.diogenesrabelo.personal_finances.controller.cashout;

import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutResponse;
import br.com.diogenesrabelo.personal_finances.service.cashout.CashoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cashout")
public class CashoutController {

    @Autowired
    private CashoutService cashoutService;

    @GetMapping
    public ResponseEntity<List<CashoutResponse>> findAll(@AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashoutService.findAll(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashoutResponse> findById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashoutService.findByIdAndUser(id, user));
    }

    @PostMapping
    public ResponseEntity<CashoutResponse> save(@RequestBody CashoutRequest cashoutRequest, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        CashoutResponse savedSaida = cashoutService.createCashout(cashoutRequest, user);
        return ResponseEntity.ok(savedSaida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashoutResponse> update(@PathVariable Long id,
                                                  @RequestBody CashoutRequest cashoutRequest,
                                                  @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashoutService.updateCashout(id, cashoutRequest, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        cashoutService.deleteById(id, user);
        return ResponseEntity.notFound().build();
    }
}
