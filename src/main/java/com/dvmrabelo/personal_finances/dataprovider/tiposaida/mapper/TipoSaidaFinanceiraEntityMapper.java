package com.dvmrabelo.personal_finances.dataprovider.tiposaida.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

public class TipoSaidaFinanceiraEntityMapper {

    public static TipoSaidaFinanceira toEntity(TipoSaidaFinanceiraInput tipoSaidaFinanceiraInput, UserEntity user) {
        return new TipoSaidaFinanceira(
                tipoSaidaFinanceiraInput.nome(),
                tipoSaidaFinanceiraInput.descricao(),
                tipoSaidaFinanceiraInput.ativo(),
                user
        );
    }

    public static TipoSaidaFinanceiraOutput toDomain(TipoSaidaFinanceira tipoSaidaFinanceira) {
        return new TipoSaidaFinanceiraOutput(
                tipoSaidaFinanceira.id(),
                tipoSaidaFinanceira.nome(),
                tipoSaidaFinanceira.descricao(),
                tipoSaidaFinanceira.getCreatedBy(),
                tipoSaidaFinanceira.isAtivo()
        );
    }
}
