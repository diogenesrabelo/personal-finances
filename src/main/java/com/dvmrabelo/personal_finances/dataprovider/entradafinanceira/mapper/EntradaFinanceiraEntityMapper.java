package com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;


public class EntradaFinanceiraEntityMapper {

    public static EntradaFinanceira toEntity(EntradaFinanceiraInput entradaFinanceira, UserEntity userEntity, TipoEntradaFinanceira tipoEntradaFinanceira) {
        return new EntradaFinanceira(
                entradaFinanceira.data(),
                entradaFinanceira.valor(),
                tipoEntradaFinanceira,
                userEntity,
                entradaFinanceira.descricao()
        );
    }

    public static EntradaFinanceiraOutput toDomain(EntradaFinanceira entradaFinanceira) {
        return new EntradaFinanceiraOutput(
                entradaFinanceira.getId(),
                entradaFinanceira.getData(),
                entradaFinanceira.getValor(),
                entradaFinanceira.getId(),
                entradaFinanceira.getCreatedBy(),
                entradaFinanceira.getDescricao()
        );
    }
}
