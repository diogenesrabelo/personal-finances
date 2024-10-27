package br.com.diogenesrabelo.personal_finances.controller.cashintype;

import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeResponse;
import br.com.diogenesrabelo.personal_finances.service.cashintype.CashinTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cashin-type")
public class CashinTypeController {

    @Autowired
    private CashinTypeService cashinTypeService;

    @GetMapping
    public ResponseEntity<List<CashinTypeResponse>> findAll(@AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashinTypeService.findAll(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashinTypeResponse> findById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashinTypeService.findById(id, user));
    }

    @PostMapping
    public ResponseEntity<CashinTypeResponse> save(@RequestBody CashinTypeRequest cashinTypeRequest, @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        CashinTypeResponse savedTipo = cashinTypeService.createCashinType(cashinTypeRequest, user);
        return ResponseEntity.ok(savedTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashinTypeResponse> update(@PathVariable Long id,
                                                     @RequestBody CashinTypeRequest cashinTypeRequest,
                                                     @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(cashinTypeService.updateCashinType(id, cashinTypeRequest, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id,
                                           @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        cashinTypeService.deactivate(id, user);
        return ResponseEntity.noContent().build();
    }
}