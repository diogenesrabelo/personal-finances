package br.com.diogenesrabelo.personal_finances.service.cashout;

import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutResponse;

import java.util.List;

public interface CashoutService {

    List<CashoutResponse> findAll(String user);

    CashoutResponse findByIdAndUser(Long id, String user);

    CashoutResponse createCashout(CashoutRequest cashoutRequest, String user);

    CashoutResponse updateCashout(Long id, CashoutRequest cashoutRequest, String user);

    void deleteById(Long id, String user);
}
