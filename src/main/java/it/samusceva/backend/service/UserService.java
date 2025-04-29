package it.samusceva.backend.service;

import it.samusceva.backend.model.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> getOneByEmail(String userEmail);

}
