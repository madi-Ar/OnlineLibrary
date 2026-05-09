package main.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import main.dto.UserDto;
import main.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userDto));
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<UserDto> updateName(@PathVariable Long id,
                                              @NotBlank(message = "Name should not be null or empty")
                                              @Size(min = 2,max = 15, message = "Name must be between 2 and 15 characters")
                                              @RequestParam String name){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.changeName(id, name));
    }

    @PatchMapping("/{id}/age")
    public ResponseEntity<UserDto> updateAge(@PathVariable Long id,
                                             @Min(value = 0, message = "Age should be greater than 0")
                                             @Max(value = 100, message = "Age must be less than 100")
                                             @RequestParam int age){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.changeAge(id, age));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<UserDto> updateEmail(@PathVariable Long id,
                                               @NotBlank(message = "email should not be null or empty")
                                               @Email(message = "not valid email")
                                               @RequestParam String email){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.changeEmail(id, email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
