package br.com.diogenesrabelo.personal_finances.controller.cashin;

import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinResponse;
import br.com.diogenesrabelo.personal_finances.service.cashin.CashinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cashin")
public class CashinController {

    @Autowired
    private CashinService cashinService;

    @GetMapping
    public ResponseEntity<List<CashinResponse>> findAll(@AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashinService.findAll(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashinResponse> findById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashinService.findById(id, user));
    }

    @PostMapping
    public ResponseEntity<CashinResponse> save(@RequestBody CashinRequest cashinRequest, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        CashinResponse savedEntrada = cashinService.createCashin(cashinRequest, user);
        return ResponseEntity.ok(savedEntrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashinResponse> update(@PathVariable Long id,
                                                 @RequestBody CashinRequest cashinRequest,
                                                 @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashinService.updateCashin(id, cashinRequest, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        cashinService.deleteById(id, user);
        return ResponseEntity.notFound().build();
    }
}