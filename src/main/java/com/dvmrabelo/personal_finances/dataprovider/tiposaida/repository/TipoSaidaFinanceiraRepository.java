package com.dvmrabelo.personal_finances.dataprovider.tiposaida.repository;

import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoSaidaFinanceiraRepository extends JpaRepository<TipoSaidaFinanceira, Long> {

    Optional<TipoSaidaFinanceira> findByUserIdAndId(Long userId, Long id);

    List<TipoSaidaFinanceira> findAllByUserId(Long userId);
}