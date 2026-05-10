package main.exceptions;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    public UserException(Long id){
        super("Entity \"user\" with id " + id + " not found");
    }
}
