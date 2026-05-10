package main.exceptions;

public class LibraryCardException extends RuntimeException {
    public LibraryCardException(String message) {
        super(message);
    }

    public LibraryCardException(Long id){
        super("Entity \"library card\" with id " + id + " not found");
    }
}
