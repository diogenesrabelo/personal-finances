package com.dvmrabelo.personal_finances.dataprovider.tipoentrada;

import com.dvmrabelo.personal_finances.core.domain.input.TipoEntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.TipoEntradaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.mapper.TipoEntradaFinanceiraEntityMapper;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.repository.TipoEntradaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoEntradaFinanceiraGatewayImpl implements TipoEntradaFinanceiraGateway {

    @Autowired
    private TipoEntradaFinanceiraRepository tipoEntradaFinanceiraRepository;

    public TipoEntradaFinanceiraOutput save(TipoEntradaFinanceiraInput tipoEntradaFinanceiraInput, UserEntity user) {
        return TipoEntradaFinanceiraEntityMapper.toDomain(
                tipoEntradaFinanceiraRepository.save(
                        TipoEntradaFinanceiraEntityMapper.toEntity(tipoEntradaFinanceiraInput, user)
                )
        );
    }

    public TipoEntradaFinanceiraOutput update(TipoEntradaFinanceiraInput tipoEntradaFinanceiraInput, Long tipoEntradaFinanceiraId) {
        TipoEntradaFinanceira tipoEntradaFinanceira = tipoEntradaFinanceiraRepository.findById(tipoEntradaFinanceiraId).get();
        TipoEntradaFinanceira updated = new TipoEntradaFinanceira(
                tipoEntradaFinanceira.id(),
                tipoEntradaFinanceiraInput.nome(),
                tipoEntradaFinanceiraInput.descricao(),
                tipoEntradaFinanceira.isAtivo(),
                tipoEntradaFinanceira.getCreatedBy()
        );
        return TipoEntradaFinanceiraEntityMapper.toDomain(
                tipoEntradaFinanceiraRepository.save(updated)
        );

    }

    public void delete(Long id) {
        tipoEntradaFinanceiraRepository.deleteById(id);
    }

    public TipoEntradaFinanceiraOutput findById(Long userId, Long id) {
        return TipoEntradaFinanceiraEntityMapper.toDomain(
                tipoEntradaFinanceiraRepository.findByUserIdAndId(userId, id).get()
        );
    }

    public TipoEntradaFinanceiraOutput findById(Long id) {
        return TipoEntradaFinanceiraEntityMapper.toDomain(
                tipoEntradaFinanceiraRepository.findById(id).get()
        );
    }

    public List<TipoEntradaFinanceiraOutput> findAll(Long userId) {
        return tipoEntradaFinanceiraRepository.findAllByUserId(userId).stream().map(TipoEntradaFinanceiraEntityMapper::toDomain).toList();
    }

    public void deactivateTipoEntrada(Long id) {
        var tipo = tipoEntradaFinanceiraRepository.findById(id).get();

        TipoEntradaFinanceira desativado = new TipoEntradaFinanceira(
                tipo.id(),
                tipo.nome(),
                tipo.descricao(),
                false,
                tipo.getCreatedBy()
        );
        tipoEntradaFinanceiraRepository.save(tipo);
    }
}
