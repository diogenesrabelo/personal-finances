package com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TipoSaidaFinanceiraMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    public TipoSaidaFinanceiraInput toInput(TipoSaidaFinanceiraInputDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    public TipoSaidaFinanceiraOutputDTO toOutputDto(TipoSaidaFinanceiraOutput dto);
}
