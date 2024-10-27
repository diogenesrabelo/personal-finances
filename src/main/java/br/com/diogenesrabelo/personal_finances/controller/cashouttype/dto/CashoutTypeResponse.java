package br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto;

public record CashoutTypeResponse(
        Long id,
        String name,
        String description,
        boolean active
) {
}
