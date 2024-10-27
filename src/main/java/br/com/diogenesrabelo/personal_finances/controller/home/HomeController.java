package br.com.diogenesrabelo.personal_finances.controller.home;

import br.com.diogenesrabelo.personal_finances.controller.home.dto.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/token")
    ResponseEntity<Token> getToken(@AuthenticationPrincipal OidcUser user) {
        return ResponseEntity.ok(new Token("Bearer", user.getIdToken().getTokenValue()));
    }
}
