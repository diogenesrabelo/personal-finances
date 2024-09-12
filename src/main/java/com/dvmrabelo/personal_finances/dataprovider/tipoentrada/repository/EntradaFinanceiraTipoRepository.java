package com.dvmrabelo.personal_finances.dataprovider.tipoentrada.repository;

import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.EntradaFinanceiraTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntradaFinanceiraTipoRepository extends JpaRepository<EntradaFinanceiraTipo, Long> {

    Optional<EntradaFinanceiraTipo> findByUserIdAndId(Long userId, Long id);

    List<EntradaFinanceiraTipo> findAllByUserId(Long userId);
}