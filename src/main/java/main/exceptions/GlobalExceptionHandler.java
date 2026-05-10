package main.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookException.class, BorrowRecordException.class,
    LibraryCardException.class, UserException.class, EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, "Not found", ex.getMessage(), request.getRequestURI(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String, String> validationError = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            validationError.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400,"Method argument not valid", "Invalid input data", request.getRequestURI(), validationError));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex,
                                                               HttpServletRequest request){
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(400,
                        "Illegal argument",
                        ex.getMessage(),
                        request.getRequestURI(),
                        null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
                                                                   HttpServletRequest request){
        Map<String, String> validationErrors = new HashMap<>();
        for(ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()){
            validationErrors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return ResponseEntity.badRequest().body(new ErrorResponse(400,
                "Constraint Violation Exception",
                "Invalid input data",
                request.getRequestURI(),
                validationErrors));
    }
}
