package com.dvmrabelo.personal_finances.core.domain.output;

import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

public record TipoSaidaFinanceiraOutput(
        Long id,
        String nome,
        String descricao,
        UserEntity createdBy,
        boolean ativo
) {
}
