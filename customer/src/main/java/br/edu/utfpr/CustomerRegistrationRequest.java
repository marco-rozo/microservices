package br.edu.utfpr;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
