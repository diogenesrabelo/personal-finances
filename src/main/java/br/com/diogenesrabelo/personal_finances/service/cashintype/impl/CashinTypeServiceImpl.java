package br.com.diogenesrabelo.personal_finances.service.cashintype.impl;

import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashintype.CashinTypeRepository;
import br.com.diogenesrabelo.personal_finances.repository.cashintype.entity.CashinType;
import br.com.diogenesrabelo.personal_finances.service.cashintype.CashinTypeService;
import br.com.diogenesrabelo.personal_finances.service.cashintype.mapper.CashinTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashinTypeServiceImpl implements CashinTypeService {

    @Autowired
    private CashinTypeRepository cashinTypeRepository;

    @Override
    public List<CashinTypeResponse> findAll(String user) {
        return cashinTypeRepository.findByUser(user)
            .stream()
            .map(CashinTypeMapper::entityToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public CashinTypeResponse findById(Long id, String user) {
        return CashinTypeMapper.entityToResponse(
            cashinTypeRepository.findByUserAndId(user, id).orElseThrow());
    }

    @Override
    public CashinTypeResponse createCashinType(CashinTypeRequest cashinTypeRequest, String user) {
        return this.save(CashinTypeMapper.requestToEntity(cashinTypeRequest, user));
    }

    @Override
    public void deactivate(Long id, String user) {
        var cashinType = cashinTypeRepository.findByUserAndId(user, id);
        cashinType.orElseThrow().disable();
        this.save(cashinType.get());
    }

    @Override
    public CashinTypeResponse updateCashinType(Long id, CashinTypeRequest cashinTypeRequest, String user) {
        return this.save(CashinTypeMapper.requestToEntity(id, cashinTypeRequest, user));
    }

    private CashinTypeResponse save(CashinType cashinType) {
        return CashinTypeMapper.entityToResponse(cashinTypeRepository.save(cashinType));
    }
}
