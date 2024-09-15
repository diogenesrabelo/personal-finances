package com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraOutputDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T11:27:46-0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
public class TipoSaidaFinanceiraMapperImpl implements TipoSaidaFinanceiraMapper {

    @Override
    public TipoSaidaFinanceiraInput toInput(TipoSaidaFinanceiraInputDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String nome = null;
        String descricao = null;
        boolean ativo = false;

        nome = dto.nome();
        descricao = dto.descricao();
        ativo = dto.ativo();

        TipoSaidaFinanceiraInput tipoSaidaFinanceiraInput = new TipoSaidaFinanceiraInput( nome, descricao, ativo );

        return tipoSaidaFinanceiraInput;
    }

    @Override
    public TipoSaidaFinanceiraOutputDTO toOutputDto(TipoSaidaFinanceiraOutput dto) {
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

        TipoSaidaFinanceiraOutputDTO tipoSaidaFinanceiraOutputDTO = new TipoSaidaFinanceiraOutputDTO( id, nome, descricao, ativo );

        return tipoSaidaFinanceiraOutputDTO;
    }
}
