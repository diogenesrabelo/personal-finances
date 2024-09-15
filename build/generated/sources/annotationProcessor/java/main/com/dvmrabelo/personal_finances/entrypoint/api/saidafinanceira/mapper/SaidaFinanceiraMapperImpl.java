package com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.mapper;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraOutputDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T11:27:46-0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
public class SaidaFinanceiraMapperImpl implements SaidaFinanceiraMapper {

    @Override
    public SaidaFinanceiraInput toInput(SaidaFinanceiraInputDTO inputDTO) {
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

        SaidaFinanceiraInput saidaFinanceiraInput = new SaidaFinanceiraInput( data, valor, tipoId, descricao );

        return saidaFinanceiraInput;
    }

    @Override
    public SaidaFinanceiraOutputDTO toOutputDto(SaidaFinanceiraOutput output) {
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

        SaidaFinanceiraOutputDTO saidaFinanceiraOutputDTO = new SaidaFinanceiraOutputDTO( id, data, valor, tipoId, descricao );

        return saidaFinanceiraOutputDTO;
    }
}
