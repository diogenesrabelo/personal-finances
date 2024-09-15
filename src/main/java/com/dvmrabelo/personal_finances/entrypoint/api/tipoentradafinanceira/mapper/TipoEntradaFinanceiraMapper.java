package com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoEntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TipoEntradaFinanceiraMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    public TipoEntradaFinanceiraInput toInput(TipoEntradaFinanceiraInputDTO dto);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    @Mapping(target = "id", source = "id")
    public TipoEntradaFinanceiraOutputDTO toOutputDto(TipoEntradaFinanceiraOutput dto);
}
