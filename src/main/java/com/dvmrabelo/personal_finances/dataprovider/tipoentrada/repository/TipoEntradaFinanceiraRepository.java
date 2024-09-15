package com.dvmrabelo.personal_finances.dataprovider.tipoentrada.repository;

import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoEntradaFinanceiraRepository extends JpaRepository<TipoEntradaFinanceira, Long> {

    Optional<TipoEntradaFinanceira> findByUserIdAndId(Long userId, Long id);

    List<TipoEntradaFinanceira> findAllByUserId(Long userId);
}