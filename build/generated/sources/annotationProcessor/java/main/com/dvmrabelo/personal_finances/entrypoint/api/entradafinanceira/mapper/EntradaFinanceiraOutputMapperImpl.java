package com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraOutputDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T10:00:32-0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
public class EntradaFinanceiraOutputMapperImpl implements EntradaFinanceiraOutputMapper {

    @Override
    public EntradaFinanceiraInput toInput(EntradaFinanceiraInputDTO inputDTO) {
        if ( inputDTO == null ) {
            return null;
        }

        Long tipoId = null;
        BigDecimal valor = null;
        LocalDate data = null;
        String descricao = null;

        tipoId = inputDTO.tipoId();
        valor = inputDTO.valor();
        data = inputDTO.data();
        descricao = inputDTO.descricao();

        EntradaFinanceiraInput entradaFinanceiraInput = new EntradaFinanceiraInput( data, valor, tipoId, descricao );

        return entradaFinanceiraInput;
    }

    @Override
    public EntradaFinanceiraOutputDTO toOutputDto(EntradaFinanceiraOutput output) {
        if ( output == null ) {
            return null;
        }

        Long tipoId = null;
        BigDecimal valor = null;
        LocalDate data = null;
        String descricao = null;
        Long id = null;

        tipoId = output.tipoId();
        valor = output.valor();
        data = output.data();
        descricao = output.descricao();
        id = output.id();

        EntradaFinanceiraOutputDTO entradaFinanceiraOutputDTO = new EntradaFinanceiraOutputDTO( id, data, valor, tipoId, descricao );

        return entradaFinanceiraOutputDTO;
    }
}
