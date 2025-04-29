package it.samusceva.backend.keycloak;

import it.samusceva.backend.model.User;
import it.samusceva.backend.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserReader {
    private final UserService userService;

    public Optional<User> getByEmail(String email) {
        return userService.getOneByEmail(email);
    }
}
