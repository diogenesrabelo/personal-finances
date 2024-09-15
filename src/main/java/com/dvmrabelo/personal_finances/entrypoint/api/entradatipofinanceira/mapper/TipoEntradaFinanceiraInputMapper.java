package com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraTipoInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraTipoOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.dto.EntradaFinanceiraTipoInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.dto.EntradaFinanceiraTipoOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TipoEntradaFinanceiraInputMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    public EntradaFinanceiraTipoInput toInput(EntradaFinanceiraTipoInputDTO dto);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "ativo", source = "ativo")
    @Mapping(target = "id", source = "id")
    public EntradaFinanceiraTipoOutputDTO toOutputDto(EntradaFinanceiraTipoOutput dto);
}
