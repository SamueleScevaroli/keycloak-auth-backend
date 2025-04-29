package it.samusceva.backend.controller;

import it.samusceva.backend.dto.UserDto;
import it.samusceva.backend.mapper.UserMapper;
import it.samusceva.backend.model.User;
import it.samusceva.backend.service.UsersApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersApplicationService usersApplicationService;
    private final UserMapper userMapper;


    @GetMapping("/get-authenticated-user")
    public UserDto getAuthenticatedUser(@AuthenticationPrincipal Jwt user,
                                        @RequestParam boolean forceResync) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUserWithSync(user, forceResync);
        return userMapper.toDto(authenticatedUser);
    }

}
