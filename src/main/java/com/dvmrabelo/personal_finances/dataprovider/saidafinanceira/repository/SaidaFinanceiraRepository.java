package com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.repository;

import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.entity.SaidaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaidaFinanceiraRepository extends JpaRepository<SaidaFinanceira, Long> {

        Optional<SaidaFinanceira> findByUserIdAndId(Long userId, Long id);

        List<SaidaFinanceira> findAllByUserId(Long userId);

        void deleteByUserId(Long userId);

        @Query("SELECT s FROM SaidaFinanceira s WHERE YEAR(s.data) = :ano AND MONTH(s.data) = :mes")
        List<SaidaFinanceira> findByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);

        @Query("SELECT s FROM SaidaFinanceira s WHERE YEAR(s.data) = :ano")
        List<SaidaFinanceira> findByAno(@Param("ano") int ano);
}