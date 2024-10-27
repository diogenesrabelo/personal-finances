package br.com.diogenesrabelo.personal_finances.service.cashouttype;

import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeResponse;

import java.util.List;

public interface CashoutTypeService {

    List<CashoutTypeResponse> findAll(String user);

    CashoutTypeResponse findById(Long id, String user);

    CashoutTypeResponse createCashoutType(CashoutTypeRequest cashoutTypeRequest, String user);

    void deactivate(Long id, String user);

    CashoutTypeResponse updateCashoutType(Long id, CashoutTypeRequest cashoutTypeRequest, String user);
}