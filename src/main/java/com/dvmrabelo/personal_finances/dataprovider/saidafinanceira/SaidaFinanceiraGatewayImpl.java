package com.dvmrabelo.personal_finances.dataprovider.saidafinanceira;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.SaidaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.mapper.SaidaFinanceiraEntityMapper;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.repository.SaidaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.repository.TipoSaidaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaidaFinanceiraGatewayImpl implements SaidaFinanceiraGateway {

    @Autowired
    private SaidaFinanceiraRepository saidaFinanceiraRepository;

    @Autowired
    private TipoSaidaFinanceiraRepository tipoSaidaFinanceiraRepository;

    public void delete(Long id) {
        saidaFinanceiraRepository.deleteById(id);
    }

    public SaidaFinanceiraOutput findById(Long userId, Long id) {
        return SaidaFinanceiraEntityMapper.toDomain(saidaFinanceiraRepository.findByUserIdAndId(userId, id).get());
    }

    public SaidaFinanceiraOutput save(SaidaFinanceiraInput saidaFinanceiraInput, UserEntity userEntity) {
        TipoSaidaFinanceira tipoSaidaFinanceira = tipoSaidaFinanceiraRepository.findById(saidaFinanceiraInput.tipoId()).get();
        return SaidaFinanceiraEntityMapper.toDomain(saidaFinanceiraRepository.save(
                SaidaFinanceiraEntityMapper.toEntity(saidaFinanceiraInput, userEntity, tipoSaidaFinanceira)
        ));
    }

    public List<SaidaFinanceiraOutput> findAll(Long userId) {
        return saidaFinanceiraRepository.findAllByUserId(userId).stream().map(SaidaFinanceiraEntityMapper::toDomain).toList();
    }
}
