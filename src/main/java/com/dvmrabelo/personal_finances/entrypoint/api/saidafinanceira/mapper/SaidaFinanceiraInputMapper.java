package com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SaidaFinanceiraInputMapper {

    @Mapping(target = "tipoId", source = "tipoId")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "data", source = "data")
    @Mapping(target = "descricao", source = "descricao")
    public SaidaFinanceiraInput toInput(SaidaFinanceiraInputDTO inputDTO);

    @Mapping(target = "tipoId", source = "tipoId")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "data", source = "data")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "id", source = "id")
    public SaidaFinanceiraOutputDTO toOutputDto(SaidaFinanceiraOutput output);
}
