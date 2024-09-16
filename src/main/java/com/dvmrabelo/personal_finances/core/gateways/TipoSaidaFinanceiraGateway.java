package com.dvmrabelo.personal_finances.core.gateways;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

import java.util.List;

public interface TipoSaidaFinanceiraGateway {

    TipoSaidaFinanceiraOutput save(TipoSaidaFinanceiraInput tipoSaidaFinanceiraInput, UserEntity user);

    TipoSaidaFinanceiraOutput update(TipoSaidaFinanceiraInput tipoSaidaFinanceiraInput, Long tipoSaidaFinanceiraId);

    void delete(Long id);

    TipoSaidaFinanceiraOutput findByUserIdAndId(Long userId, Long id);

    TipoSaidaFinanceiraOutput findById(Long id);

    List<TipoSaidaFinanceiraOutput> findAllByUserId(Long userId);

    void deactivateTipoSaida(Long id);
}
