package br.com.diogenesrabelo.personal_finances.controller.cashin.dto;

import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CashinResponse(
    Long id,
    LocalDate date,
    BigDecimal value,
    CashinTypeResponse type,
    String description
) {
}
