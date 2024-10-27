package br.com.diogenesrabelo.personal_finances.service.cashout.impl;

import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashout.CashoutRepository;
import br.com.diogenesrabelo.personal_finances.repository.cashout.entity.Cashout;
import br.com.diogenesrabelo.personal_finances.service.cashout.CashoutService;
import br.com.diogenesrabelo.personal_finances.service.cashout.mapper.CashoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashoutServiceImpl implements CashoutService {

    @Autowired
    private CashoutRepository cashoutRepository;

    @Override
    public List<CashoutResponse> findAll(String user) {
        return cashoutRepository.findByUser(user)
            .stream()
            .map(CashoutMapper::entityToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public CashoutResponse findByIdAndUser(Long id, String user) {
        return CashoutMapper.entityToResponse(
            cashoutRepository.findByIdAndUser(id, user).orElseThrow());
    }

    @Override
    public CashoutResponse createCashout(CashoutRequest cashoutRequest, String user) {
        return this.save(CashoutMapper.requestToEntity(cashoutRequest, user));
    }

    @Override
    public CashoutResponse updateCashout(Long id, CashoutRequest cashoutRequest, String user) {
         return this.save(CashoutMapper.requestToEntity(id, cashoutRequest, user));
    }

    @Override
    public void deleteById(Long id, String user) {
        cashoutRepository.deleteByIdAndUser(id, user);
    }

    private CashoutResponse save(Cashout cashout) {
        return CashoutMapper.entityToResponse(cashoutRepository.save(cashout));
    }
}
