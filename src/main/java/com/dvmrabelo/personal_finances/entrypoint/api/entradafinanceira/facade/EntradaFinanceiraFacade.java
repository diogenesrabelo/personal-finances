package com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.facade;

import com.dvmrabelo.personal_finances.core.usecases.EntradaFinanceiraUseCase;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.dto.EntradaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.entradafinanceira.mapper.EntradaFinanceiraMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntradaFinanceiraFacade {

    @Autowired
    private EntradaFinanceiraUseCase entradaFinanceiraUseCase;

    @Autowired
    private EntradaFinanceiraMapper entradaFinanceiraMapper;

    public void removeEntradaFinanceira(Long id) {
        entradaFinanceiraUseCase.deleteById(id);
    }

    public EntradaFinanceiraOutputDTO findById(Long id) {
        return entradaFinanceiraMapper.toOutputDto(entradaFinanceiraUseCase.findById(id));
    }

    public List<EntradaFinanceiraOutputDTO> findAll() {
        return entradaFinanceiraUseCase.findAll().stream().map(entradaFinanceiraMapper::toOutputDto).toList();
    }

    public EntradaFinanceiraOutputDTO createEntradaFinanceira(EntradaFinanceiraInputDTO entradaFinanceiraInputDTO) {
        return entradaFinanceiraMapper.toOutputDto(entradaFinanceiraUseCase.createEntradaFinanceira(entradaFinanceiraMapper.toInput(entradaFinanceiraInputDTO)));
    }

    public EntradaFinanceiraOutputDTO updateEntradaFinanceira(Long id, EntradaFinanceiraInputDTO entradaFinanceiraInputDTO) {
        return entradaFinanceiraMapper.toOutputDto(entradaFinanceiraUseCase.updateEntradaFinanceira(id, entradaFinanceiraMapper.toInput(entradaFinanceiraInputDTO)));
    }
}
