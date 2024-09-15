package com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraTipoInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraTipoOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.dto.EntradaFinanceiraTipoInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradatipofinanceira.dto.EntradaFinanceiraTipoOutputDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T10:00:32-0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
public class TipoEntradaFinanceiraInputMapperImpl implements TipoEntradaFinanceiraInputMapper {

    @Override
    public EntradaFinanceiraTipoInput toInput(EntradaFinanceiraTipoInputDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String nome = null;
        String descricao = null;
        boolean ativo = false;

        nome = dto.nome();
        descricao = dto.descricao();
        ativo = dto.ativo();

        EntradaFinanceiraTipoInput entradaFinanceiraTipoInput = new EntradaFinanceiraTipoInput( nome, descricao, ativo );

        return entradaFinanceiraTipoInput;
    }

    @Override
    public EntradaFinanceiraTipoOutputDTO toOutputDto(EntradaFinanceiraTipoOutput dto) {
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

        EntradaFinanceiraTipoOutputDTO entradaFinanceiraTipoOutputDTO = new EntradaFinanceiraTipoOutputDTO( id, nome, descricao, ativo );

        return entradaFinanceiraTipoOutputDTO;
    }
}
