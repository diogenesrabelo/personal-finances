package br.com.diogenesrabelo.personal_finances.controller.goal;

import br.com.diogenesrabelo.personal_finances.controller.goal.dto.GoalsRequest;
import br.com.diogenesrabelo.personal_finances.controller.goal.dto.Message;
import br.com.diogenesrabelo.personal_finances.controller.goal.dto.GoalResponse;
import br.com.diogenesrabelo.personal_finances.service.goal.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping
    public ResponseEntity<Message> createGoals(@RequestBody GoalsRequest goalsRequest,
                                               @AuthenticationPrincipal Jwt jwt) {
        var user = jwt.getClaim("email").toString();
        goalService.processGoals(goalsRequest, user);

        return ResponseEntity.ok(new Message("Em até 10 minutos seus objetivos serão gerados e você terá " +
            "acesso às melhores segestões para melhorar sua vida financeira!"));
    }

    @GetMapping
    public ResponseEntity<GoalResponse> smartGoals(@AuthenticationPrincipal Jwt jwt) throws ChangeSetPersister.NotFoundException {
        var user = jwt.getClaim("email").toString();
        return ResponseEntity.ok(goalService.getResponse(user));
    }
}
