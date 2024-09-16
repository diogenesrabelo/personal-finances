package com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.entity.SaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

public class SaidaFinanceiraEntityMapper {

    public static SaidaFinanceira toEntity(SaidaFinanceiraInput saidaFinanceiraInput, UserEntity userEntity, TipoSaidaFinanceira tipoSaidaFinanceira) {
        return new SaidaFinanceira(
                saidaFinanceiraInput.data(),
                saidaFinanceiraInput.valor(),
                tipoSaidaFinanceira,
                userEntity,
                saidaFinanceiraInput.descricao()
        );
    }

    public static SaidaFinanceiraOutput toDomain(SaidaFinanceira saidaFinanceira) {
        return new SaidaFinanceiraOutput(
                saidaFinanceira.getId(),
                saidaFinanceira.getData(),
                saidaFinanceira.getValor(),
                saidaFinanceira.getTipo().id(),
                saidaFinanceira.getCreatedBy(),
                saidaFinanceira.getDescricao()
        );
    }
}
