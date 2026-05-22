package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.UserDto;
import main.dto.formDto.UserFormDto;
import main.dto.securityDto.AuthDto;
import main.dto.securityDto.JwtResponce;
import main.dto.securityDto.LoginDto;
import main.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
                jwtUtil.createToken(formDto.getEmail(), "USER"),
                userDto
        );
    }

    public JwtResponce login(LoginDto login) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword())
        );
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        assert userDetails != null;
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        return new JwtResponce(jwtUtil.createToken(userDetails.getUsername(), role));
    }
}
