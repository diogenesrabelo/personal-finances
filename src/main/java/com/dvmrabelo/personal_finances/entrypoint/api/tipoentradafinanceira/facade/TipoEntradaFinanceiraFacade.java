package com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.facade;

import com.dvmrabelo.personal_finances.core.usecases.EntradaFinanceiraTipoUseCase;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraInputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraOutputDTO;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.mapper.TipoEntradaFinanceiraMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoEntradaFinanceiraFacade {

    @Autowired
    private EntradaFinanceiraTipoUseCase entradaFinanceiraTipoUseCase;

    @Autowired
    private TipoEntradaFinanceiraMapper tipoEntradaFinanceiraMapper;

    public TipoEntradaFinanceiraOutputDTO createTipoEntradaFinanceira(TipoEntradaFinanceiraInputDTO tipoEntradaFinanceiraInputDTO) {
        return tipoEntradaFinanceiraMapper.toOutputDto(entradaFinanceiraTipoUseCase.createEntradaFinanceiraTipo(tipoEntradaFinanceiraMapper.toInput(tipoEntradaFinanceiraInputDTO)));
    }

    public TipoEntradaFinanceiraOutputDTO updateTipoEntradaFinanceira(Long id, TipoEntradaFinanceiraInputDTO tipoEntradaFinanceiraInputDTO) {
        return tipoEntradaFinanceiraMapper.toOutputDto(entradaFinanceiraTipoUseCase.updateEntradaFinanceiraTipo(id, tipoEntradaFinanceiraMapper.toInput(tipoEntradaFinanceiraInputDTO)));
    }

    public void deactivateTipoEntradaFinanceira(Long id) {
        entradaFinanceiraTipoUseCase.deactivate(id);
    }

    public TipoEntradaFinanceiraOutputDTO findById(Long id) {
        return tipoEntradaFinanceiraMapper.toOutputDto(entradaFinanceiraTipoUseCase.findById(id).get());
    }

    public List<TipoEntradaFinanceiraOutputDTO> findAll() {
        return entradaFinanceiraTipoUseCase.findAll().stream().map(tipoEntradaFinanceiraMapper::toOutputDto).toList();
    }
}
