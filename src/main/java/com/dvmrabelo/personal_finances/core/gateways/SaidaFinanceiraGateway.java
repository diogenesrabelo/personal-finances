package com.dvmrabelo.personal_finances.core.gateways;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

import java.util.List;

public interface SaidaFinanceiraGateway {

    SaidaFinanceiraOutput save(SaidaFinanceiraInput saidaFinanceiraInput, UserEntity userEntity);

    void deleteById(Long id);

    SaidaFinanceiraOutput findByUserIdAndId(Long userId, Long id);

    SaidaFinanceiraOutput findById(Long id);

    List<SaidaFinanceiraOutput> findAllByUserId(Long userId);

    SaidaFinanceiraOutput update(SaidaFinanceiraInput saidaFinanceiraInput, Long saidaFinanceiraId);
}
