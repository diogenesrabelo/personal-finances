package com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraTipoInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraTipoOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.dto.SaidaFinanceiraTipoInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.dto.SaidaFinanceiraTipoOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TipoSaidaFinanceiraInputMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    public SaidaFinanceiraTipoInput toInput(SaidaFinanceiraTipoInputDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    public SaidaFinanceiraTipoOutputDTO toOutputDto(SaidaFinanceiraTipoOutput dto);
}
