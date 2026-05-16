package main.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.dto.LibraryCardDto;
import main.service.LibraryCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class LibraryCardController {
    private final LibraryCardService cardService;

    @PostMapping
    public ResponseEntity<LibraryCardDto> create(@Valid @RequestBody LibraryCardDto cardDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardService.create(cardDto));
    }

    @GetMapping
    public ResponseEntity<List<LibraryCardDto>> findAll(){
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryCardDto> findById(@PathVariable Long id){
        return ResponseEntity.of(cardService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LibraryCardDto> updateDate(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cardService.changeDateOfCreation(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        cardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
