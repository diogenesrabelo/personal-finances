package br.com.diogenesrabelo.personal_finances.service.cashouttype.mapper;

import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashouttype.entity.CashoutType;

public class CashoutTypeMapper {

    public static CashoutType requestToEntity(CashoutTypeRequest cashoutTypeRequest, String user) {
        return new CashoutType().buildCashoutType(
            cashoutTypeRequest.name(),
            cashoutTypeRequest.description(),
            cashoutTypeRequest.active(),
            user
        );
    }

    public static CashoutType requestToEntity(Long id, CashoutTypeRequest cashoutTypeRequest, String user) {
        return new CashoutType().buildCashoutTypeAllArgs(
            id,
            cashoutTypeRequest.name(),
            cashoutTypeRequest.description(),
            cashoutTypeRequest.active(),
            user
        );
    }

    public static CashoutType responseToEntity(CashoutTypeResponse cashoutTypeResponse, String user) {
        return new CashoutType().buildCashoutTypeAllArgs(
            cashoutTypeResponse.id(),
            cashoutTypeResponse.name(),
            cashoutTypeResponse.description(),
            cashoutTypeResponse.active(),
            user
        );
    }

    public static CashoutTypeResponse entityToResponse(CashoutType cashoutType) {
        return new CashoutTypeResponse(
            cashoutType.getId(),
            cashoutType.getName(),
            cashoutType.getDescription(),
            cashoutType.isActive()
        );
    }
}
