package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.UserDto;
import main.dto.formDto.UserFormDto;
import main.dto.securityDto.AuthDto;
import main.dto.securityDto.LoginDto;
import main.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthDto registration(UserFormDto formDto){
        UserDto userDto = userService.create(formDto);
        return new AuthDto(
                jwtUtil.createToken(formDto.getEmail()),
                userDto
        );
    }

    public String login(LoginDto login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword())
        );
        return jwtUtil.createToken(login.getEmail());
    }
}
