package br.com.diogenesrabelo.personal_finances.service.cashout.mapper;

import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashout.dto.CashoutResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashout.entity.Cashout;
import br.com.diogenesrabelo.personal_finances.service.cashouttype.mapper.CashoutTypeMapper;

public class CashoutMapper {

    public static Cashout requestToEntity(CashoutRequest cashoutRequest, String user) {
        return new Cashout().buildCashout(
            cashoutRequest.date(),
            cashoutRequest.value(),
            cashoutRequest.description(),
            CashoutTypeMapper.responseToEntity(cashoutRequest.type(), user),
            user);
    }

    public static Cashout requestToEntity(Long id, CashoutRequest cashoutRequest, String user) {
        return new Cashout().buildCashoutAllArgs(
            id,
            cashoutRequest.date(),
            cashoutRequest.value(),
            cashoutRequest.description(),
            CashoutTypeMapper.responseToEntity(cashoutRequest.type(), user),
            user);
    }

    public static CashoutResponse entityToResponse(Cashout cashout) {
        return new CashoutResponse(
            cashout.getId(),
            cashout.getDate(),
            cashout.getValue(),
            CashoutTypeMapper.entityToResponse(cashout.getType()),
            cashout.getDescription()
        );
    }
}
