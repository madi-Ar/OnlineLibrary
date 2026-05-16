package main.dto;

import lombok.Getter;
import lombok.Setter;
import main.entity.BorrowRecord;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowRecordDto {
    private Long id;
    private Long cardId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public static BorrowRecordDto mapToRecordDto(BorrowRecord record){
        BorrowRecordDto recordDto = new BorrowRecordDto();
        recordDto.setId(record.getId());
        if(record.getCard() != null) {
            recordDto.setCardId(record.getCard().getId());
        }
        if(record.getBook() != null) {
            recordDto.setBookId(record.getBook().getId());
        }
        recordDto.setBorrowDate(record.getBorrowDate());
        recordDto.setReturnDate(record.getBorrowDate().plusWeeks(2));
        return recordDto;
    }

    public static BorrowRecord mapToRecordEntity(){
        BorrowRecord record = new BorrowRecord();
        record.setBorrowDate(LocalDate.now());
        return record;
    }
}
