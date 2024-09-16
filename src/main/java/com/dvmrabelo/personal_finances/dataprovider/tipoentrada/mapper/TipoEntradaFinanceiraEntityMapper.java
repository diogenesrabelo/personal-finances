package com.dvmrabelo.personal_finances.dataprovider.tipoentrada.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoEntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;

public class TipoEntradaFinanceiraEntityMapper {

    public static TipoEntradaFinanceira toEntity(TipoEntradaFinanceiraInput tipoEntradaFinanceiraInput, UserEntity user) {
        return new TipoEntradaFinanceira(
                tipoEntradaFinanceiraInput.nome(),
                tipoEntradaFinanceiraInput.descricao(),
                tipoEntradaFinanceiraInput.ativo(),
                user
        );
    }

    public static TipoEntradaFinanceiraOutput toDomain(TipoEntradaFinanceira tipoEntradaFinanceira) {
        return new TipoEntradaFinanceiraOutput(
                tipoEntradaFinanceira.id(),
                tipoEntradaFinanceira.nome(),
                tipoEntradaFinanceira.descricao(),
                tipoEntradaFinanceira.getCreatedBy(),
                tipoEntradaFinanceira.isAtivo()
        );
    }
}
