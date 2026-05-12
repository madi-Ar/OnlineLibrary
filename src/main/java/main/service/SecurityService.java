package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.UserDto;
import main.dto.formDto.UserFormDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserService userService;

    public UserDto registration(UserFormDto formDto){
        return userService.create(formDto);
    }
}
