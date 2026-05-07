package main.dto;

import lombok.Getter;
import lombok.Setter;
import main.entity.BorrowRecord;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class BorrowRecordDto {
    private Long id;
    private Long cardId;
    private Long BookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public static BorrowRecordDto mapToRecordDto(BorrowRecord record){
        BorrowRecordDto recordDto = new BorrowRecordDto();
        recordDto.setId(record.getId());
        recordDto.setCardId(record.getCard().getId());
        recordDto.setBookId(record.getBook().getId());
        recordDto.setBorrowDate(record.getBorrowDate());
        recordDto.setReturnDate(record.getBorrowDate().plusWeeks(2));
        return recordDto;
    }

    public static BorrowRecord mapToRecordEntity(BorrowRecordDto recordDto){
        BorrowRecord record = new BorrowRecord();
        record.setBorrowDate(LocalDate.now());
        return record;
    }
}
