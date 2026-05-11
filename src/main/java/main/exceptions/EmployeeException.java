package main.exceptions;

public class EmployeeException extends RuntimeException {
    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException(Long id){
        super("Entity \"employee\" with id: "+id+" not found");
    }
}
