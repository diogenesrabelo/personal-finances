package br.com.diogenesrabelo.personal_finances.controller.cashouttype.dto;

public record CashoutTypeRequest(
        String name,
        String description,
        boolean active
) {
}
