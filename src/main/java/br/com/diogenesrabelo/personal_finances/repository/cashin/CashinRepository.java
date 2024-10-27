package br.com.diogenesrabelo.personal_finances.repository.cashin;

import br.com.diogenesrabelo.personal_finances.repository.cashin.entity.Cashin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashinRepository extends JpaRepository<Cashin, Long> {

    Optional<Cashin> findByUserAndId(String user, Long id);

    List<Cashin> findByUser(String user);

    void deleteByIdAndUser(Long id, String user);

    @Query("SELECT c FROM Cashin c " +
        " INNER JOIN CashinType ct " +
        " ON c.type.id = ct.id " +
        " WHERE YEAR(c.date) = :year AND MONTH(c.date) = :month AND ct.active = true AND c.user = :user")
    List<Cashin> findByYearAndMonth(@Param("year") int year, @Param("month") int month, @Param("user") String user);

    @Query("SELECT c FROM Cashin c " +
        "INNER JOIN CashinType ct " +
        "ON c.type.id = ct.id " +
        "WHERE YEAR(c.date) = :year AND ct.active = true AND c.user = :user")
    List<Cashin> findByYear(@Param("year") int year, @Param("user") String user);
}