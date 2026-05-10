package main.exceptions;

public class BookException extends RuntimeException {
    public BookException(String message) {
        super(message);
    }

    public BookException(Long id){
        super("Entity \"book\" with id " + id + " not found");
    };
}
