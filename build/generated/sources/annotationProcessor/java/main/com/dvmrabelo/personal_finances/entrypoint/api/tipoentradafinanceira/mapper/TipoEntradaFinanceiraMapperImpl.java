package com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.TipoEntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraOutputDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T21:18:25-0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
public class TipoEntradaFinanceiraMapperImpl implements TipoEntradaFinanceiraMapper {

    @Override
    public TipoEntradaFinanceiraInput toInput(TipoEntradaFinanceiraInputDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String nome = null;
        String descricao = null;
        boolean ativo = false;

        nome = dto.nome();
        descricao = dto.descricao();
        ativo = dto.ativo();

        TipoEntradaFinanceiraInput tipoEntradaFinanceiraInput = new TipoEntradaFinanceiraInput( nome, descricao, ativo );

        return tipoEntradaFinanceiraInput;
    }

    @Override
    public TipoEntradaFinanceiraOutputDTO toOutputDto(TipoEntradaFinanceiraOutput dto) {
        if ( dto == null ) {
            return null;
        }

        String nome = null;
        String descricao = null;
        boolean ativo = false;
        Long id = null;

        nome = dto.nome();
        descricao = dto.descricao();
        ativo = dto.ativo();
        id = dto.id();

        TipoEntradaFinanceiraOutputDTO tipoEntradaFinanceiraOutputDTO = new TipoEntradaFinanceiraOutputDTO( id, nome, descricao, ativo );

        return tipoEntradaFinanceiraOutputDTO;
    }
}
