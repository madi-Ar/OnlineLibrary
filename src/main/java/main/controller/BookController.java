package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.BookDto;
import main.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.create(bookDto));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(){
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Long id){
        return ResponseEntity.of(bookService.findById(id));
    }

    @PatchMapping("/{id}/title")
    public ResponseEntity<BookDto> changeTitle(@PathVariable Long id,
                                               @RequestParam String title){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(bookService.changeTitle(id, title));
    }

    @PatchMapping("/{id}/author")
    public ResponseEntity<BookDto> changeAuthor(@PathVariable Long id,
                                                @RequestParam String author){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(bookService.changeAuthor(id, author));
    }

    @PatchMapping("/{id}/year")
    public ResponseEntity<BookDto> changeYear(@PathVariable Long id,
                                              @RequestParam int year){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(bookService.changeYear(id, year));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
