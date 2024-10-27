package br.com.diogenesrabelo.personal_finances.controller.cashout.dto;

import br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto.CashoutTypeResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CashoutRequest(
        LocalDate date,
        BigDecimal value,
        CashoutTypeResponse type,
        String description
) {
}
