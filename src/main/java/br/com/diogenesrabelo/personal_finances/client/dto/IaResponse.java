package br.com.diogenesrabelo.personal_finances.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IaResponse(
    String model,
    @JsonProperty("created_at")
    String createdAt,
    String response
) {
}
