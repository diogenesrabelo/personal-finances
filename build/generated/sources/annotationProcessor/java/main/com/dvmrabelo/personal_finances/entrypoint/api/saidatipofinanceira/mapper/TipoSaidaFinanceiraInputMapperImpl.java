package com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraTipoInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraTipoOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.dto.SaidaFinanceiraTipoInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidatipofinanceira.dto.SaidaFinanceiraTipoOutputDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T10:00:32-0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
public class TipoSaidaFinanceiraInputMapperImpl implements TipoSaidaFinanceiraInputMapper {

    @Override
    public SaidaFinanceiraTipoInput toInput(SaidaFinanceiraTipoInputDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String nome = null;
        String descricao = null;
        boolean ativo = false;

        nome = dto.nome();
        descricao = dto.descricao();
        ativo = dto.ativo();

        SaidaFinanceiraTipoInput saidaFinanceiraTipoInput = new SaidaFinanceiraTipoInput( nome, descricao, ativo );

        return saidaFinanceiraTipoInput;
    }

    @Override
    public SaidaFinanceiraTipoOutputDTO toOutputDto(SaidaFinanceiraTipoOutput dto) {
        if ( dto == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String descricao = null;
        boolean ativo = false;

        id = dto.id();
        nome = dto.nome();
        descricao = dto.descricao();
        ativo = dto.ativo();

        SaidaFinanceiraTipoOutputDTO saidaFinanceiraTipoOutputDTO = new SaidaFinanceiraTipoOutputDTO( id, nome, descricao, ativo );

        return saidaFinanceiraTipoOutputDTO;
    }
}
