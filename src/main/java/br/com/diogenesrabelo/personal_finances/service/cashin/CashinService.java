package br.com.diogenesrabelo.personal_finances.service.cashin;

import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinResponse;

import java.util.List;

public interface CashinService {

    List<CashinResponse> findAll(String user);

    CashinResponse findById(Long id, String user);

    CashinResponse createCashin(CashinRequest cashinRequest, String user);
    CashinResponse updateCashin(Long id, CashinRequest cashinRequest, String user);

    void deleteById(Long id, String user);
}
