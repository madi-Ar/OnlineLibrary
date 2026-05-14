package main.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.dto.UserDto;
import main.dto.formDto.UserFormDto;
import main.service.SecurityService;
import main.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@Valid @RequestBody UserFormDto formDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(formDto));
    }
}
