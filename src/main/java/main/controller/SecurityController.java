package main.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.dto.formDto.UserFormDto;
import main.dto.securityDto.AuthDto;
import main.dto.securityDto.JwtResponce;
import main.dto.securityDto.LoginDto;
import main.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/registration")
    public ResponseEntity<AuthDto> registration(@Valid @RequestBody UserFormDto user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(securityService.registration(user));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponce> login(@Valid @RequestBody LoginDto login){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(securityService.login(login));
    }
}
