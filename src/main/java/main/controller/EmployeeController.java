package main.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.dto.EmployeeDto;
import main.dto.formDto.EmployeeFormDto;
import main.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeers")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeFormDto formDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.create(formDto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(employeeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id,
                                              @Valid @RequestBody EmployeeFormDto formDto){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(employeeService.update(id, formDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
