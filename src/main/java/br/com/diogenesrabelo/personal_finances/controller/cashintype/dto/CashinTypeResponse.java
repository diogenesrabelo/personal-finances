package br.com.diogenesrabelo.personal_finances.controller.cashintype.dto;

public record CashinTypeResponse(
        Long id,
        String name,
        String description,
        boolean active

) {
}
