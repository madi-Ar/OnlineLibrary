package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.UserDto;
import main.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userDto));
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return ResponseEntity.of(service.findById(id));
    }

    //почему нельзя писать переменные в теле запроса без аннотации RequestBody?
    @PatchMapping("/{id}/name")
    public ResponseEntity<UserDto> updateName(@PathVariable Long id,@RequestParam String name){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.changeName(id, name));
    }

    //почему нельзя писать переменные в теле запроса без аннотации RequestBody?
    @PatchMapping("/{id}/age")
    public ResponseEntity<UserDto> updateAge(@PathVariable Long id, @RequestParam int age){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.changeAge(id, age));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
