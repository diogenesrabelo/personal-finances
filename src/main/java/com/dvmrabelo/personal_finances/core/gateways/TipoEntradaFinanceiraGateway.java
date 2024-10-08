package com.dvmrabelo.personal_finances.core.gateways;

import com.dvmrabelo.personal_finances.core.domain.input.TipoEntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

import java.util.List;

public interface TipoEntradaFinanceiraGateway {

    TipoEntradaFinanceiraOutput save(TipoEntradaFinanceiraInput tipoEntradaFinanceiraInput, UserEntity user);

    TipoEntradaFinanceiraOutput update(TipoEntradaFinanceiraInput tipoEntradaFinanceiraInput, Long tipoEntradaFinanceiraId);

    void delete(Long id);

    TipoEntradaFinanceiraOutput findById(Long userId, Long id);

    TipoEntradaFinanceiraOutput findById(Long id);

    List<TipoEntradaFinanceiraOutput> findAll(Long userId);

    void deactivateTipoEntrada(Long id);
}
