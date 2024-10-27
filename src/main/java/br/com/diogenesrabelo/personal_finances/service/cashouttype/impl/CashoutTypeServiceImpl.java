package br.com.diogenesrabelo.personal_finances.service.cashouttype.impl;

import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashouttype.CashoutTypeRepository;
import br.com.diogenesrabelo.personal_finances.repository.cashouttype.entity.CashoutType;
import br.com.diogenesrabelo.personal_finances.service.cashouttype.CashoutTypeService;
import br.com.diogenesrabelo.personal_finances.service.cashouttype.mapper.CashoutTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashoutTypeServiceImpl implements CashoutTypeService {

    @Autowired
    private CashoutTypeRepository cashoutTypeRepository;

    public List<CashoutTypeResponse> findAll(String user) {
        return cashoutTypeRepository.findByUser(user)
            .stream()
            .map(CashoutTypeMapper::entityToResponse)
            .collect(Collectors.toList());
    }

    public CashoutTypeResponse findById(Long id, String user) {
        return CashoutTypeMapper.entityToResponse(cashoutTypeRepository.findByUserAndId(user, id)
            .orElseThrow());
    }

    public CashoutTypeResponse createCashoutType(CashoutTypeRequest cashoutTypeRequest, String user) {
        return this.save(CashoutTypeMapper.requestToEntity(cashoutTypeRequest, user));
    }

    public void deactivate(Long id, String user) {
        var cashoutType = cashoutTypeRepository.findByUserAndId(user, id);
        cashoutType.orElseThrow().disable();
        this.save(cashoutType.get());
    }

    public CashoutTypeResponse updateCashoutType(Long id, CashoutTypeRequest cashoutTypeRequest, String user) {
        return this.save(CashoutTypeMapper.requestToEntity(id, cashoutTypeRequest, user));
    }

    private CashoutTypeResponse save(CashoutType cashoutType){
        return CashoutTypeMapper.entityToResponse(cashoutTypeRepository.save(cashoutType));
    }
}
