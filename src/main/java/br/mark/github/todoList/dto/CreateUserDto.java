package br.mark.github.todoList.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        @Size(min = 8)
        String password)
{
}
