package it.samusceva.backend.service;

import it.samusceva.backend.keycloak.UserReader;
import it.samusceva.backend.keycloak.UserSynchronizer;
import it.samusceva.backend.model.AuthenticatedUser;
import it.samusceva.backend.model.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersApplicationService {

    private final UserSynchronizer userSynchronizer;
    private final UserReader userReader;

    public UsersApplicationService(UserService userRepository) {
        this.userSynchronizer = new UserSynchronizer(userRepository);
        this.userReader = new UserReader(userRepository);
    }

    @Transactional
    public User getAuthenticatedUserWithSync(Jwt oauth2User, boolean forceResync) {
        userSynchronizer.syncWithIdp(oauth2User, forceResync);
        return userReader.getByEmail(AuthenticatedUser.username().get())
                .orElseThrow();
    }
}
