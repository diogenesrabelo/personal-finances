package br.com.diogenesrabelo.personal_finances.controller.home.dto;

public record Token(
    String type,
    String jwt) {
}
