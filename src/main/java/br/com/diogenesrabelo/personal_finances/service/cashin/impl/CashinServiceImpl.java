package br.com.diogenesrabelo.personal_finances.service.cashin.impl;

import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashin.CashinRepository;
import br.com.diogenesrabelo.personal_finances.repository.cashin.entity.Cashin;
import br.com.diogenesrabelo.personal_finances.service.cashin.CashinService;
import br.com.diogenesrabelo.personal_finances.service.cashin.mapper.CashinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashinServiceImpl implements CashinService {

    @Autowired
    private CashinRepository cashinRepository;

    @Override
    public List<CashinResponse> findAll(String user) {

        return cashinRepository.findByUser(user)
            .stream().
            map(CashinMapper::entityToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public CashinResponse findById(Long id, String user) {

        return CashinMapper.entityToResponse(
            cashinRepository.findByUserAndId(user, id).orElseThrow());
    }

    @Override
    public CashinResponse createCashin(CashinRequest cashinRequest, String user) {

        return this.save(CashinMapper.requestToEntity(cashinRequest, user));
    }

    @Override
    public CashinResponse updateCashin(Long id, CashinRequest cashinRequest, String user) {
        return this.save(CashinMapper.requestToEntity(cashinRequest, user, id));
    }

    @Override
    public void deleteById(Long id, String user) {
        cashinRepository.deleteByIdAndUser(id, user);
    }

    private CashinResponse save(Cashin cashin) {
        return CashinMapper.entityToResponse(cashinRepository.save(cashin));
    }
}
