package br.com.diogenesrabelo.personal_finances.controller.cashintype.dto;

public record CashinTypeRequest(
        String name,
        String description,
        boolean active
) {}
