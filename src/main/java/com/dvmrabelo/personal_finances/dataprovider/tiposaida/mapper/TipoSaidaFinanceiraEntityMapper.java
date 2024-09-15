package com.dvmrabelo.personal_finances.dataprovider.tiposaida.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;

public class TipoSaidaFinanceiraEntityMapper {

    public static TipoSaidaFinanceira toEntity(TipoSaidaFinanceiraInput tipoSaidaFinanceiraInput) {
        return new TipoSaidaFinanceira(
                tipoSaidaFinanceiraInput.nome(),
                tipoSaidaFinanceiraInput.descricao(),
                tipoSaidaFinanceiraInput.ativo()
        );
    }

    public static TipoSaidaFinanceiraOutput toDomain(TipoSaidaFinanceira tipoSaidaFinanceira) {
        return new TipoSaidaFinanceiraOutput(
                tipoSaidaFinanceira.id(),
                tipoSaidaFinanceira.nome(),
                tipoSaidaFinanceira.descricao(),
                tipoSaidaFinanceira.isAtivo()
        );
    }
}
