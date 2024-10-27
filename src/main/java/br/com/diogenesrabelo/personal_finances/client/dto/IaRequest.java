package br.com.diogenesrabelo.personal_finances.client.dto;

public record IaRequest(
    String model,
    String prompt,
    boolean stream
) {
}
