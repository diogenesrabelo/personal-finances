package br.com.diogenesrabelo.personal_finances.service.goal;

import br.com.diogenesrabelo.personal_finances.controller.goal.dto.GoalResponse;
import br.com.diogenesrabelo.personal_finances.controller.goal.dto.GoalsRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface GoalService {

    @Async
    void processGoals(GoalsRequest goals, String user);

    GoalResponse getResponse(String user) throws ChangeSetPersister.NotFoundException;
}
