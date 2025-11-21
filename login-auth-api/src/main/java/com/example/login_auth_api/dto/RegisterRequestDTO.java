package com.example.login_auth_api.dto;

public record RegisterRequestDTO(String name, String email, String password) {}//aqui receberemos como parâmetro os strings mencionados, lembrando que o record vem com os getters automaticos

