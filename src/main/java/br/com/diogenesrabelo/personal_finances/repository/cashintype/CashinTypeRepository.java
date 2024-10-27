package br.com.diogenesrabelo.personal_finances.repository.cashintype;

import br.com.diogenesrabelo.personal_finances.repository.cashintype.entity.CashinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashinTypeRepository extends JpaRepository<CashinType, Long> {

    Optional<CashinType> findByUserAndId(String user, Long id);

    List<CashinType> findByUser(String user);
}