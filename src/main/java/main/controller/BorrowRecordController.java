package main.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.dto.BorrowRecordDto;
import main.service.BorrowRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class BorrowRecordController {
    private final BorrowRecordService recordService;

    @PostMapping
    public ResponseEntity<BorrowRecordDto> create(@Valid @RequestBody BorrowRecordDto recordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(recordService.create(recordDto));
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecordDto>> findAll(){
        return ResponseEntity.ok().body(recordService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowRecordDto> findById(@PathVariable Long id){
        return ResponseEntity.of(recordService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BorrowRecordDto> setBorrowDate(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(recordService.changeBorrowDate(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        recordService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
