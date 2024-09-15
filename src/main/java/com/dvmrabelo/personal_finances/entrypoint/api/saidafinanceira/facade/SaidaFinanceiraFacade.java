package com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.facade;

import com.dvmrabelo.personal_finances.core.usecases.SaidaFinanceiraUseCase;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.dto.SaidaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.saidafinanceira.mapper.SaidaFinanceiraMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaidaFinanceiraFacade {

    @Autowired
    private SaidaFinanceiraUseCase saidaFinanceiraUseCase;

    @Autowired
    private SaidaFinanceiraMapper saidaFinanceiraMapper;

    public SaidaFinanceiraOutputDTO createSaidaFinanceira(SaidaFinanceiraInputDTO saidaFinanceiraInputDTO) {
        return saidaFinanceiraMapper.toOutputDto(
                saidaFinanceiraUseCase.createSaidaFinanceira(saidaFinanceiraMapper.toInput(saidaFinanceiraInputDTO)
                );
    }

    public SaidaFinanceiraOutputDTO updateSaidaFinanceira(Long id, SaidaFinanceiraInputDTO saidaFinanceiraInputDTO) {
        return saidaFinanceiraMapper.toOutputDto(
                saidaFinanceiraUseCase.updateSaidaFinanceira(id, saidaFinanceiraMapper.toInput(saidaFinanceiraInputDTO)
                );
    }

    public void removeSaidaFinanceira(Long id) {
        saidaFinanceiraUseCase.deleteById(id);
    }

    public SaidaFinanceiraOutputDTO findById(Long id) {
        return saidaFinanceiraMapper.toOutputDto(saidaFinanceiraUseCase.findById(id).get());
    }

    public List<SaidaFinanceiraOutputDTO> findAll() {
        return saidaFinanceiraUseCase.findAll().stream().map(saidaFinanceiraMapper::toOutputDto).toList();
    }
}
