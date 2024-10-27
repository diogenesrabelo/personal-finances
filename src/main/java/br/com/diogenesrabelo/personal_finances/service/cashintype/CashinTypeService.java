package br.com.diogenesrabelo.personal_finances.service.cashintype;

import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeResponse;

import java.util.List;

public interface CashinTypeService {

    List<CashinTypeResponse> findAll(String user);

    CashinTypeResponse findById(Long id, String user);

    CashinTypeResponse createCashinType(CashinTypeRequest cashinTypeRequest, String user);

    void deactivate(Long id, String user);

    CashinTypeResponse updateCashinType(Long id, CashinTypeRequest cashinTypeRequest, String user);
}
