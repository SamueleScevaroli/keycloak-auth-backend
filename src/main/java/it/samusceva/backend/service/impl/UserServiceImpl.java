package it.samusceva.backend.service.impl;

import it.samusceva.backend.mapper.UserMapper;
import it.samusceva.backend.model.User;
import it.samusceva.backend.repository.UserRepository;
import it.samusceva.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(User user) {
        if (user.getDbId() != null) {
            userRepository.findById(user.getDbId())
                    .ifPresent(u -> {
                        u.updateFromUser(user);
                        userRepository.saveAndFlush(u);
                    });
        } else {
            userRepository.save(userMapper.toEntity(user));
        }
    }

    @Override
    public Optional<User> getOneByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(userMapper::toModel);
    }

}
