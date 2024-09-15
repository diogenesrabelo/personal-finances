package com.dvmrabelo.personal_finances.dataprovider.tipoentrada.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoEntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;

public class TipoEntradaFinanceiraEntityMapper {

    public static TipoEntradaFinanceira toEntity(TipoEntradaFinanceiraInput tipoEntradaFinanceiraInput) {
        return new TipoEntradaFinanceira(
                tipoEntradaFinanceiraInput.nome(),
                tipoEntradaFinanceiraInput.descricao(),
                tipoEntradaFinanceiraInput.ativo()
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
