package com.dvmrabelo.personal_finances.dataprovider.entradafinanceira;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.EntradaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.mapper.EntradaFinanceiraEntityMapper;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.repository.EntradaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.repository.TipoEntradaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntradaFinanceiraGatewayImpl implements EntradaFinanceiraGateway {

    @Autowired
    private EntradaFinanceiraRepository entradaFinanceiraRepository;

    @Autowired
    private TipoEntradaFinanceiraRepository tipoEntradaFinanceiraRepository;

    public void delete(Long id) {
        entradaFinanceiraRepository.deleteById(id);
    }

    public EntradaFinanceiraOutput findById(Long userId, Long id) {
        return EntradaFinanceiraEntityMapper.toDomain(entradaFinanceiraRepository.findByUserIdAndId(userId, id).get());
    }

    public EntradaFinanceiraOutput save(EntradaFinanceiraInput entradaFinanceiraInput, UserEntity userEntity) {
        TipoEntradaFinanceira tipoEntradaFinanceira = tipoEntradaFinanceiraRepository.findById(entradaFinanceiraInput.tipoId()).get();
        return EntradaFinanceiraEntityMapper.toDomain(entradaFinanceiraRepository.save(
                EntradaFinanceiraEntityMapper.toEntity(entradaFinanceiraInput, userEntity, tipoEntradaFinanceira)
        ));
    }

    public List<EntradaFinanceiraOutput> findAll(Long userId) {
        return entradaFinanceiraRepository.findAllByUserId(userId).stream().map(EntradaFinanceiraEntityMapper::toDomain).toList();
    }
}
