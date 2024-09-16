package com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.facade;

import com.dvmrabelo.personal_finances.core.usecases.SaidaFinanceiraTipoUseCase;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.dto.TipoSaidaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tiposaidafinanceira.mapper.TipoSaidaFinanceiraMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoSaidaFinanceiraFacade {

    @Autowired
    private SaidaFinanceiraTipoUseCase saidaFinanceiraTipoUseCase;

    @Autowired
    private TipoSaidaFinanceiraMapper tipoSaidaFinanceiraMapper;

    public TipoSaidaFinanceiraOutputDTO createTipoSaidaFinanceira(TipoSaidaFinanceiraInputDTO tipoSaidaFinanceiraInputDTO) {
        return tipoSaidaFinanceiraMapper.toOutputDto(saidaFinanceiraTipoUseCase.createSaidaFinanceiraTipo(tipoSaidaFinanceiraMapper.toInput(tipoSaidaFinanceiraInputDTO)));
    }

    public TipoSaidaFinanceiraOutputDTO updateTipoSaidaFinanceira(Long id, TipoSaidaFinanceiraInputDTO tipoSaidaFinanceiraInputDTO) {
        return tipoSaidaFinanceiraMapper.toOutputDto(saidaFinanceiraTipoUseCase.updateSaidaFinanceiraTipo(id, tipoSaidaFinanceiraMapper.toInput(tipoSaidaFinanceiraInputDTO)));
    }

    public void deactivateTipoSaidaFinanceira(Long id) {
        saidaFinanceiraTipoUseCase.deactivate(id);
    }

    public TipoSaidaFinanceiraOutputDTO findById(Long id) {
        return tipoSaidaFinanceiraMapper.toOutputDto(saidaFinanceiraTipoUseCase.findById(id));
    }

    public List<TipoSaidaFinanceiraOutputDTO> findAll() {
        return saidaFinanceiraTipoUseCase.findAll().stream().map(tipoSaidaFinanceiraMapper::toOutputDto).toList();
    }
}
