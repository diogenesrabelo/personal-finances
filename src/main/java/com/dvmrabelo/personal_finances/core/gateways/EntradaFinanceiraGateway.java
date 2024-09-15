package com.dvmrabelo.personal_finances.core.gateways;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

import java.util.List;

public interface EntradaFinanceiraGateway {

    void delete(Long id);

    EntradaFinanceiraOutput findById(Long userId, Long id);

    List<EntradaFinanceiraOutput> findAll(Long userId);

    EntradaFinanceiraOutput save(EntradaFinanceiraInput entradaFinanceiraInput, UserEntity userEntity);
}
