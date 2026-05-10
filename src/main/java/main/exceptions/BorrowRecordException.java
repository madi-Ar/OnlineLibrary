package main.exceptions;

public class BorrowRecordException extends RuntimeException {
    public BorrowRecordException(String message) {
        super(message);
    }

    public BorrowRecordException(Long id){
        super("Entity \"borrow record\" with id " + id + " not found");
    }
}
