package com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EntradaFinanceiraMapper {

    @Mapping(target = "tipoId", source = "tipoId")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "data", source = "data")
    @Mapping(target = "descricao", source = "descricao")
    public EntradaFinanceiraInput toInput(EntradaFinanceiraInputDTO inputDTO);

    @Mapping(target = "tipoId", source = "tipoId")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "data", source = "data")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "id", source = "id")
    public EntradaFinanceiraOutputDTO toOutputDto(EntradaFinanceiraOutput output);
}
