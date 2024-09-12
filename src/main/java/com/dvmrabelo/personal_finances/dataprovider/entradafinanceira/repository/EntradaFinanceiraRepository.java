package com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.repository;

import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntradaFinanceiraRepository extends JpaRepository<EntradaFinanceira, Long> {

    Optional<EntradaFinanceira> findByUserIdAndId(Long userId, Long id);

    List<EntradaFinanceira> findAllByUserId(Long userId);

    @Query("SELECT e FROM EntradaFinanceira e WHERE YEAR(e.data) = :ano AND MONTH(e.data) = :mes")
    List<EntradaFinanceira> findByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);

    @Query("SELECT e FROM EntradaFinanceira e WHERE YEAR(e.data) = :ano")
    List<EntradaFinanceira> findByAno(@Param("ano") int ano);
}