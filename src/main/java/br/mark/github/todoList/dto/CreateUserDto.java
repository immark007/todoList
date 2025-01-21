package br.mark.github.todoList.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
