package br.com.diogenesrabelo.personal_finances.repository.goal;

import br.com.diogenesrabelo.personal_finances.repository.goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    void deleteAllByUser(String user);

    Optional<Goal> findByUser(String user);
}
