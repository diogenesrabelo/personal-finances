package br.com.diogenesrabelo.personal_finances.repository.cashouttype;

import br.com.diogenesrabelo.personal_finances.repository.cashouttype.entity.CashoutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashoutTypeRepository extends JpaRepository<CashoutType, Long> {

    Optional<CashoutType> findByUserAndId(String user, Long id);

    List<CashoutType> findByUser(String user);
}