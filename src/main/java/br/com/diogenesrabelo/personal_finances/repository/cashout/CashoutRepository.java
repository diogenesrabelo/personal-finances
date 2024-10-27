package br.com.diogenesrabelo.personal_finances.repository.cashout;

import br.com.diogenesrabelo.personal_finances.repository.cashout.entity.Cashout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashoutRepository extends JpaRepository<Cashout, Long> {

        Optional<Cashout> findByIdAndUser(Long id, String user);

        List<Cashout> findByUser(String user);

        @Query("SELECT c FROM Cashout c " +
            "INNER JOIN CashoutType ct " +
            "ON c.type.id = ct.id " +
            "WHERE YEAR(c.date) = :year AND MONTH(c.date) = :month AND ct.active = true AND c.user = :user")
        List<Cashout> findByYearAndMonth(@Param("year") int year, @Param("month") int month, @Param("user") String user);

        @Query("SELECT c FROM Cashout c " +
            "INNER JOIN CashoutType ct " +
            "ON c.type.id = ct.id " +
            "WHERE YEAR(c.date) = :year AND ct.active = true AND c.user = :user")
        List<Cashout> findByYear(@Param("year") int year, @Param("user") String user);

        void deleteByIdAndUser(Long id, String user);
}