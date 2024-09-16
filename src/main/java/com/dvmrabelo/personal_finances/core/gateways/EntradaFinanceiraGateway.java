package com.dvmrabelo.personal_finances.core.gateways;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

import java.util.List;

public interface EntradaFinanceiraGateway {

    void delete(Long id);

    EntradaFinanceiraOutput findByUserIdAndId(Long userId, Long id);

    List<EntradaFinanceiraOutput> findAllByUserId(Long userId);

    EntradaFinanceiraOutput findById(Long id);

    EntradaFinanceiraOutput save(EntradaFinanceiraInput entradaFinanceiraInput, UserEntity userEntity);

    EntradaFinanceiraOutput update(EntradaFinanceiraInput entradaFinanceiraInput, Long entradaFinanceiraId);
}
