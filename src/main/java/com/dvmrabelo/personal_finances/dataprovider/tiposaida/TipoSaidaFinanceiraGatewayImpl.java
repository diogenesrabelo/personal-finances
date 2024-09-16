package com.dvmrabelo.personal_finances.dataprovider.tiposaida;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.TipoSaidaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.mapper.TipoSaidaFinanceiraEntityMapper;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.repository.TipoSaidaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoSaidaFinanceiraGatewayImpl implements TipoSaidaFinanceiraGateway {

    @Autowired
    private TipoSaidaFinanceiraRepository tipoSaidaFinanceiraRepository;

    public TipoSaidaFinanceiraOutput save(TipoSaidaFinanceiraInput tipoSaidaFinanceiraInput, UserEntity user) {
        return TipoSaidaFinanceiraEntityMapper.toDomain(tipoSaidaFinanceiraRepository.save(
                TipoSaidaFinanceiraEntityMapper.toEntity(tipoSaidaFinanceiraInput, user)
        ));
    }

    public TipoSaidaFinanceiraOutput update(TipoSaidaFinanceiraInput tipoSaidaFinanceiraInput, Long tipoSaidaFinanceiraId) {
        TipoSaidaFinanceira tipoSaidaFinanceira = tipoSaidaFinanceiraRepository.findById(tipoSaidaFinanceiraId).get();
        TipoSaidaFinanceira updated = new TipoSaidaFinanceira(
                tipoSaidaFinanceira.id(),
                tipoSaidaFinanceiraInput.nome(),
                tipoSaidaFinanceiraInput.descricao(),
                tipoSaidaFinanceira.isAtivo(),
                tipoSaidaFinanceira.getCreatedBy()
        );
        return TipoSaidaFinanceiraEntityMapper.toDomain(tipoSaidaFinanceiraRepository.save(updated));
    }

    public void delete(Long id) {
        tipoSaidaFinanceiraRepository.deleteById(id);
    }

    public TipoSaidaFinanceiraOutput findByUserIdAndId(Long userId, Long id) {
        return TipoSaidaFinanceiraEntityMapper.toDomain(tipoSaidaFinanceiraRepository.findByUserIdAndId(userId, id).get());
    }

    public List<TipoSaidaFinanceiraOutput> findAllByUserId(Long userId) {
        return tipoSaidaFinanceiraRepository.findAllByUserId(userId).stream().map(TipoSaidaFinanceiraEntityMapper::toDomain).toList();
    }

    public void deactivateTipoSaida(Long id) {
        var tipo = tipoSaidaFinanceiraRepository.findById(id).get();

        TipoSaidaFinanceira desativado = new TipoSaidaFinanceira(
                tipo.id(),
                tipo.nome(),
                tipo.descricao(),
                false,
                tipo.getCreatedBy()
        );

        tipoSaidaFinanceiraRepository.save(desativado);
    }

    public TipoSaidaFinanceiraOutput findById(Long id) {
        return TipoSaidaFinanceiraEntityMapper.toDomain(tipoSaidaFinanceiraRepository.findById(id).get());
    }
}
