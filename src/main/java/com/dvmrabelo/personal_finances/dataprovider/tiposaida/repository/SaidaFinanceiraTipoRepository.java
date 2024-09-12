package com.dvmrabelo.personal_finances.dataprovider.tiposaida.repository;

import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.SaidaFinanceiraTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaidaFinanceiraTipoRepository extends JpaRepository<SaidaFinanceiraTipo, Long> {

    Optional<SaidaFinanceiraTipo> findByUserIdAndId(Long userId, Long id);

    List<SaidaFinanceiraTipo> findAllByUserId(Long userId);
}