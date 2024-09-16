package com.dvmrabelo.personal_finances.dataprovider.entradafinanceira;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.EntradaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
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

    public EntradaFinanceiraOutput findByUserIdAndId(Long userId, Long id) {
        return EntradaFinanceiraEntityMapper.toDomain(entradaFinanceiraRepository.findByUserIdAndId(userId, id).get());
    }

    public EntradaFinanceiraOutput save(EntradaFinanceiraInput entradaFinanceiraInput, UserEntity userEntity) {
        TipoEntradaFinanceira tipoEntradaFinanceira = tipoEntradaFinanceiraRepository.findById(entradaFinanceiraInput.tipoId()).get();
        return EntradaFinanceiraEntityMapper.toDomain(entradaFinanceiraRepository.save(
                EntradaFinanceiraEntityMapper.toEntity(entradaFinanceiraInput, userEntity, tipoEntradaFinanceira)
        ));
    }

    public List<EntradaFinanceiraOutput> findAllByUserId(Long userId) {
        return entradaFinanceiraRepository.findAllByUserId(userId).stream().map(EntradaFinanceiraEntityMapper::toDomain).toList();
    }

    public EntradaFinanceiraOutput findById(Long id) {
        return EntradaFinanceiraEntityMapper.toDomain(entradaFinanceiraRepository.findById(id).get());
    }

    public EntradaFinanceiraOutput update(EntradaFinanceiraInput entradaFinanceiraInput, Long entradaFinanceiraId) {
        var entradaFinanceira = entradaFinanceiraRepository.findById(entradaFinanceiraId).get();
        var tipoEntradaFinanceira = tipoEntradaFinanceiraRepository.findById(entradaFinanceiraInput.tipoId()).get();
        var updated = new EntradaFinanceira(
                entradaFinanceira.getId(),
                entradaFinanceiraInput.data(),
                entradaFinanceiraInput.valor(),
                tipoEntradaFinanceira,
                entradaFinanceira.getCreatedBy(),
                entradaFinanceiraInput.descricao()
        );
        return EntradaFinanceiraEntityMapper.toDomain(entradaFinanceiraRepository.save(updated));
    }
}
