package com.dvmrabelo.personal_finances.core.gateways;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

import java.util.List;

public interface SaidaFinanceiraGateway {

    SaidaFinanceiraOutput save(SaidaFinanceiraInput saidaFinanceiraInput, UserEntity userEntity);

    void delete(Long id);

    SaidaFinanceiraOutput findById(Long userId, Long id);

    List<SaidaFinanceiraOutput> findAll(Long userId);
}
